package com.example.roompractice3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roompractice3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    private lateinit var personViewModel: PersonViewModel
    private var personToEdit: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val edtName = binding.edtName
        val edtAge = binding.edtAge
        val btnSave = binding.btnSave
        val recyclerView = binding.recyclerView


        val adapter = PersonAdapter(
            onEditClick = { person ->
                edtName.setText(person.name)
                edtAge.setText(person.age.toString()) // Convert age to String before setting it
                personToEdit = person // Store the person being edited
                btnSave.text = "Update" // Change button text to "Update"
            },
            onDeleteClick = { person ->
                personViewModel.delete(person)
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        personViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)

        personViewModel.allPersons.observe(this, Observer { persons ->
            persons?.let { adapter.setPerson(it) }
        })

        btnSave.setOnClickListener {
            val name = edtName.text.toString()
            val age = edtAge.text.toString().toIntOrNull() // Convert the input to an Int

            // Ensure that the name is not blank and the age is a valid integer
            if (name.isNotBlank() && age != null) {
                if (personToEdit != null) {
                    // If editing, update the existing person
                    val updatedPerson = personToEdit!!.copy(name = name, age = age) // Use the correct types
                    personViewModel.update(updatedPerson)
                    personToEdit = null // Clear the edit flag
                    btnSave.text = "Save" // Reset button text to "Save"
                } else {
                    // If not editing, insert a new person
                    val person = Person(name = name, age = age)
                    personViewModel.insert(person)
                }
                // Clear the input fields after saving
                edtName.text.clear()
                edtAge.text.clear()
            }
        }
    }
}