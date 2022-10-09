/*
Written by Caleb Telfer Sept. 25 2022
Purpose: Contact manager
Features: Add, delete, view, edit, sort, save, load your contacts
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class contactManagerV3 extends JFrame implements ActionListener {
    String[][] contactData = new String[999][5];
    int numOfContacts = 0;
    int flag = -1;

    //initializing jpanels, textfields, labels, and buttons.
    JPanel initJPanel = new JPanel();
    JLabel initLabel = new JLabel();

    String[] columns = new String[5];
    JPanel viewJPanel = new JPanel();

    JPanel addJPanel = new JPanel();
    JTextField addTextField = new JTextField(25);
    JLabel addLabel = new JLabel();
    JButton addButton = new JButton();

    JPanel editJPanel = new JPanel();
    JTextField editTextField = new JTextField(25);
    JLabel editLabel = new JLabel();
    JButton editButton = new JButton();

    JPanel deleteJPanel = new JPanel();
    JButton deleteButton = new JButton();
    JTextField deleteTextField = new JTextField(25);
    JLabel deleteLabel = new JLabel("Enter first name");


    public static void main(String[] args) {
        //Program window configuration
        contactManagerV3 window = new contactManagerV3();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Contact Manager");
        window.setSize(800,600);
        window.setVisible(true);
    }

    public contactManagerV3() {
        // JPANELS CONFIGURATION SECTION

        //InitJPanel
        initLabel.setText("Welcome to your contact manager! Please select an action from the menu bar!");
        initJPanel.add(initLabel, BorderLayout.CENTER);
        //Starting program with initJPanel display
        getContentPane().add(initJPanel);
        validate();

        //VIEW JPanel
        String columns[] = {"First","Last","Address","Phone#", "Email"};
        JTable viewTable = new JTable(contactData, columns);
        JScrollPane viewScrollPane = new JScrollPane(viewTable);
        viewJPanel.add(viewScrollPane, BorderLayout.CENTER);

        //ADD JPanel
        addLabel.setText("Enter Name:");
        addButton.setText("Add to records");
        addButton.addActionListener(this);
        addJPanel.add(addLabel, BorderLayout.WEST);
        addJPanel.add(addTextField, BorderLayout.CENTER);
        addJPanel.add(addButton, BorderLayout.EAST);

        //DELETE JPanel
        deleteButton.setText("Delete Contact");
        deleteButton.addActionListener(this);
        deleteJPanel.add(deleteLabel, BorderLayout.WEST);
        deleteJPanel.add(deleteTextField, BorderLayout.CENTER);
        deleteJPanel.add(deleteButton, BorderLayout.EAST);

        //EDIT JPanel
        editLabel.setText("Enter name to modify");
        editButton.setText("Find Contact");
        editButton.addActionListener(this);
        editJPanel.add(editLabel, BorderLayout.WEST);
        editJPanel.add(editTextField, BorderLayout.CENTER);
        editJPanel.add(editButton, BorderLayout.EAST);

        //Setting up main menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //The File, Modify, and View drop downs
        JMenu file = new JMenu("File");
        JMenu modify = new JMenu("Modify");
        JMenu view = new JMenu("View");
        menuBar.add(file);
        menuBar.add(modify);
        menuBar.add(view);

        //Items in the 'File' drop down
        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem quit = new JMenuItem("Quit");
        file.add(load);
        file.add(save);
        file.add(quit);

        //Items in the 'Modify' drop down
        JMenuItem add = new JMenuItem("Add");
        JMenuItem delete = new JMenuItem("Delete");
        JMenuItem edit = new JMenuItem("Edit");
        modify.add(add);
        modify.add(delete);
        modify.add(edit);

        //Items in the 'View' drop down
        JMenuItem viewAll = new JMenuItem("View All");
        JMenu sort = new JMenu("Sort by...");
        view.add(viewAll);
        view.add(sort);
        JMenuItem alphabetically = new JMenuItem("Alphabet");
        sort.add(alphabetically);

        //Adding actionlisteners for each drop down button.
        load.addActionListener(this);
        save.addActionListener(this);
        quit.addActionListener(this);
        add.addActionListener(this);
        viewAll.addActionListener(this);
        delete.addActionListener(this);
        edit.addActionListener(this);
        alphabetically.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Options: Load, save, quit, add, delete, edit, view, sort
        String event = e.getActionCommand();

        if(event.equals("Load")){
            try {
                Load();
            } catch (IOException ioerror) {
                System.out.println("IOError" +ioerror);
            }
        };

        if(event.equals("Save")){
            try {
                Save();
            } catch (IOException ioerror) {
                System.out.println("IOError" +ioerror);
            }
        };

        if(event.equals("Quit")){System.exit(0);};

        //Add drop down: Display the Panel
        if(event.equals("Add")){
            getContentPane().removeAll();
            getContentPane().add(addJPanel);
            revalidate();
            repaint();
        };

        //Add to records button: Begin Adding Contact
        if(event.equals("Add to records")){Add();};

        //Delete drop down: Display the Panel
        if(event.equals("Delete")){
            getContentPane().removeAll();
            deleteLabel.setText("Enter first name");
            getContentPane().add(deleteJPanel);
            revalidate();
            repaint();
        };

        //Delete Contact BUTTON: Begin Deleting Contact
        if(event.equals("Delete Contact")) {Delete();};

        //Edit drop down: Display the Panel
        if(event.equals("Edit")) {
            getContentPane().removeAll();
            editLabel.setText("Enter name to modify:");
            editButton.setText("Find Contact");
            getContentPane().add(editJPanel);
            revalidate();
            repaint();
        }

        //Update Button: Begins updating the contact data
        if(event.equals("Update")) {ModifyUpdate();};

        //Find Contact Button: Checks if contact exists
        if(event.equals("Find Contact")) {Modify();};

        //Menu dropdown: Sort by... -> Alphabet
        if(event.equals("Alphabet")){Sort();};

        if(event.equals("View All")){View();};

    }
    public void Load() throws IOException {
        // Loading in contacts to contactData[][].
        BufferedReader in = new BufferedReader(new FileReader("myContacts.txt"));
        numOfContacts = Integer.valueOf(in.readLine());

        for(int i = 0; i < numOfContacts; i++) {
            contactData[i][0] = in.readLine();
            contactData[i][1] = in.readLine();
            contactData[i][2] = in.readLine();
            contactData[i][3] = in.readLine();
            contactData[i][4] = in.readLine();
        }
        in.close();
        View();
    }

    public void Save() throws IOException {
        //Saving contacts from contactData[][] to myContacts.txt
        PrintWriter fileout = new PrintWriter(new FileWriter("myContacts.txt"));
        fileout.println(numOfContacts);

        for(int i = 0; i<numOfContacts; i++) {
            fileout.println(contactData[i][0]);
            fileout.println(contactData[i][1]);
            fileout.println(contactData[i][2]);
            fileout.println(contactData[i][3]);
            fileout.println(contactData[i][4]);
        }

        fileout.close();
    }

    public void View() {
        //Updating program to the View Panel.
        getContentPane().removeAll();
        getContentPane().add(viewJPanel);
        revalidate();
        repaint();
    }

    public void Add() {
        /*
        Checks the labels requested entry
        Inserts input into appropriate element of the contactData array
        Updates the label for the next piece of the contacts data

         */
        switch (addLabel.getText()) {
            case "Enter Name:": {contactData[numOfContacts][0] = addTextField.getText(); addLabel.setText("Enter Last Name:"); break;}
            case "Enter Last Name:": {contactData[numOfContacts][1] = addTextField.getText(); addLabel.setText("Enter Address:"); break;}
            case "Enter Address:": {contactData[numOfContacts][2] = addTextField.getText(); addLabel.setText("Enter Phone #:"); break;}
            case "Enter Phone #:": {contactData[numOfContacts][3] = addTextField.getText(); addLabel.setText("Enter Email:"); break;}
            case "Enter Email:": {
                contactData[numOfContacts][4] = addTextField.getText();
                addLabel.setText("Enter Name:");
                numOfContacts = numOfContacts+1;
                getContentPane().removeAll();
                getContentPane().add(initJPanel);
                revalidate();
                repaint();
                break;
            }
        }
        addTextField.setText("");
    }

    public void Delete() {
        //Deletes a contact. Search contactData[numofCon][0-4]
        String searchName = deleteTextField.getText();
        boolean found = false;

        //if contact is found in the database it is removed.
        for(int i = 0; i <numOfContacts; i++) {
            if(searchName.equalsIgnoreCase(contactData[i][0])) {
                contactData[i][0] = "";
                contactData[i][1] = "";
                contactData[i][2] = "";
                contactData[i][3] = "";
                contactData[i][4] = "";
                numOfContacts = numOfContacts-1;

                //Update to the default screen if successful.
                found = true;
                getContentPane().removeAll();
                getContentPane().add(initJPanel);
                revalidate();
                repaint();
            }
        }
        if(!found) {
            deleteLabel.setText("User not found");
        }
    }

    public void Modify () {
        String modifyName = editTextField.getText();
        flag = -1;

        //Checks if contact exists, if so begins update process in ModifyUpdate()
        for(int i = 0; i<numOfContacts; i++) {
            if(modifyName.equalsIgnoreCase(contactData[i][0])) {
                flag = i;
            }
        }

        if(flag == -1) {
            editLabel.setText("User not found!");
        } else {
            editLabel.setText("Enter New Name:");
            //Updating button for a new actionlistener that handles when the client exists
            editButton.setText("Update");

        }

    }

    public void ModifyUpdate() {
        /*
        Checks the labels requested entry
        Inserts input into appropriate element of the contactData array
        Updates the label for the next piece of the contacts data

         */
        switch (editLabel.getText()) {
            case "Enter New Name:": {contactData[flag][0] = editTextField.getText(); editLabel.setText("Enter New Last Name:"); break;}
            case "Enter New Last Name:": {contactData[flag][1] = editTextField.getText(); editLabel.setText("Enter New Address:"); break;}
            case "Enter New Address:": {contactData[flag][2] = editTextField.getText(); editLabel.setText("Enter New Phone #:"); break;}
            case "Enter New Phone #:": {contactData[flag][3] = editTextField.getText(); editLabel.setText("Enter New Email:"); break;}
            case "Enter New Email:": {
                contactData[flag][4] = editTextField.getText();
                getContentPane().removeAll();
                getContentPane().add(initJPanel);
                revalidate();
                repaint();
                break;
            }
        }
        editTextField.setText("");
    }

    public void Sort() {
        String[] temp = new String[5];

        //Compares elements side by side and swaps them if they're not in alphabetical order
        for(int i =0; i<numOfContacts; i++) {
            for(int j = i+ 1; j<numOfContacts; j++) {
                if(contactData[i][0].compareToIgnoreCase(contactData[j][0]) > 0) {

                    temp[0] = contactData[i][0];
                    temp[1] = contactData[i][1];
                    temp[2] = contactData[i][2];
                    temp[3] = contactData[i][3];
                    temp[4] = contactData[i][4];

                    contactData[i][0] = contactData[j][0];
                    contactData[i][1] = contactData[j][1];
                    contactData[i][2] = contactData[j][2];
                    contactData[i][3] = contactData[j][3];
                    contactData[i][4] = contactData[j][4];

                    contactData[j][0] = temp[0];
                    contactData[j][1] = temp[1];
                    contactData[j][2] = temp[2];
                    contactData[j][3] = temp[3];
                    contactData[j][4] = temp[4];

                }
            }
        }
        View();
    }

}