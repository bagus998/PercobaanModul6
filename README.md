Percobaan praktikum kali ini yaitu membuat aplikasi kontak dengan menggunakan recycler view untuk menampilkan daftar nama dan nomor telepon
Bagus Setiawan - 235150707111057


The application is a contact management system that allows users to add, view, and manage contacts.
It uses a RecyclerView to display a list of contacts, and each contact can be interacted with to call, message, or view details.

1. ContactActivity Class

Purpose: This is the main activity that manages the contact list and user interactions.

Key Components:

RecyclerView: Displays the list of contacts.
EditText Fields: For inputting contact details (name, number, Instagram, group).
Buttons: For submitting new contacts and clearing input fields.
Logic:

onCreate() Method:

Initializes UI components and sets up the RecyclerView.
Sets a LinearLayoutManager to the RecyclerView for vertical scrolling.
Initializes the contact list with some predefined contacts.
Sets up click listeners for buttons to handle user interactions.
Adding Contacts:

When the user fills in the details and clicks the submit button, a new ContactModel is created and added to the contactList.
A new ContactAdapter is instantiated with the updated contact list and set to the RecyclerView.
Displaying Contacts:

The RecyclerView is updated to show the new contact list whenever a contact is added or removed.
Clearing Input Fields:

The clearData() method resets the EditText fields to empty.

2. ContactAdapter Class

Purpose: This class is responsible for binding the contact data to the views in the RecyclerView.

Key Components:

ViewHolder: Holds the views for each contact item (name, number, buttons for call, message, WhatsApp).
ClickListener: Interface to handle item click events.
Logic:

onCreateViewHolder():

Inflates the layout for each contact item and creates a ViewHolder.
onBindViewHolder():

Binds the contact data to the views in the ViewHolder.
Sets up click listeners for call, message, and WhatsApp actions.
When a contact item is clicked, it starts the DetailContactActivity with the contact's details.
getItemCount():

Returns the size of the contact list, which determines how many items the RecyclerView will display.

3. ContactModel Class

Purpose: Represents a contact with attributes like name, number, group, and Instagram.

Logic:

Contains getter and setter methods for each attribute, allowing the adapter to access and modify contact data.

4. DetailContactActivity Class

Purpose: Displays detailed information about a selected contact.

Logic:

Retrieves contact details passed from the ContactAdapter and displays them.
Provides buttons to call, message, or view the contact's Instagram profile.
Includes a back link to return to the previous activity.

5. RecyclerView Functionality

Initialization: The RecyclerView is initialized in ContactActivity with a LinearLayoutManager, which allows for vertical scrolling.

Data Binding: The ContactAdapter binds the contact data to the RecyclerView. Each time the contact list is updated (adding or removing contacts), the adapter is notified, and the RecyclerView refreshes its display.

Item Click Handling: The adapter uses a ClickListener interface to handle item clicks, allowing for actions like viewing details or deleting a contact.

In summary, the application effectively uses a RecyclerView to manage and display a dynamic list of contacts, allowing for user interaction through various buttons and activities. The separation of concerns between the activity, adapter, and model classes follows good practices in Android development.
