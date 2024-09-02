package com.example.roompractice3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter(
    private val onEditClick: (Person) -> Unit,
    private val onDeleteClick: (Person) -> Unit
) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    private var personList = emptyList<Person>()

    inner class PersonViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val name = v.findViewById<TextView>(R.id.tvName)
        val age = v.findViewById<TextView>(R.id.tvAge)
        val edit = v.findViewById<Button>(R.id.btnEdit)
        val delete = v.findViewById<Button>(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return PersonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = personList[position]
        holder.name.text = person.name
        holder.age.text = person.age.toString()

        holder.edit.setOnClickListener {
            onEditClick(person)
        }

        holder.delete.setOnClickListener {
            onDeleteClick(person)
        }
    }

    fun setPerson(persons: List<Person>) {
        this.personList = persons
        notifyDataSetChanged()
    }
}