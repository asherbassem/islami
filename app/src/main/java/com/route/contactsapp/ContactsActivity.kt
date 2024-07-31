package com.route.contactsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.route.contactsapp.databinding.ActivityContactsBinding

class ContactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactsBinding
    private lateinit var contactRecyclerView: RecyclerView
    private lateinit var adapter: ContactsAdapter
    private lateinit var contactList: MutableList<ContactsItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nameFocusListener()
        phoneFocusListener()
        binding.savebtn.setOnClickListener { saveForm() }

        contactRecyclerView = binding.contactsRecyler
        contactList = mutableListOf()
        adapter = ContactsAdapter(contactList)
        adapter.onItemClick = object : OnContactItemClickListner {
            override fun onContactsItemClick(contactItem: ContactsItem, position: Int) {
                val intent = Intent(this@ContactsActivity, ContactsDetailsActivity::class.java)
                intent.putExtra("name", contactItem.name)
                intent.putExtra("phone", contactItem.phone)
                intent.putExtra("description", contactItem.description)
                startActivity(intent)
            }
        }
        contactRecyclerView.adapter = adapter
    }

    private fun saveForm() {
        val nameError = validateName()
        val phoneError = validatePhone()

        binding.nameContainer.error = nameError
        binding.phoneContainer.error = phoneError

        val isValidName = nameError == null
        val isValidPhone = phoneError == null

        if (isValidName && isValidPhone) {
            resetForm()
        }
    }



    private fun resetForm() {
        val name = binding.nameET.text.toString()
        val phone = binding.phoneET.text.toString()
        val description = binding.descripitionET.text.toString()

        val newContact = ContactsItem(name, phone, description)
        contactList.add(newContact)
        adapter.notifyItemInserted(contactList.size - 1)

        binding.nameET.text?.clear()
        binding.phoneET.text?.clear()
        binding.descripitionET.text?.clear()
    }

    private fun nameFocusListener() {
        binding.nameET.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.nameContainer.helperText = validateName()
            }
        }
    }

    private fun validateName(): String? {
        val nameText = binding.nameET.text.toString()
        return if (nameText.length < 3) {
            "Minimum 3 characters for name"
        } else {
            null
        }
    }

    private fun phoneFocusListener() {
        binding.phoneET.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.phoneContainer.helperText = validatePhone()
            }
        }
    }

    private fun validatePhone(): String? {
        val phoneText = binding.phoneET.text.toString()
        return if (phoneText.length != 11) {
            "Must be 11 digits"
        } else {
            null
        }
    }
}
