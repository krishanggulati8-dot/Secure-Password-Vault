# 🔐 Simple Password Vault with Secure GUI

A desktop-based credential management system built with **Java Swing** for secure storage and retrieval of website credentials.

---

## 👥 Team

| Name | Roll Number |
|------|-------------|
| Krishang Gulati | 590016563 |
| Arpit Parashar | 590013122 |
| Madhur Mehta | 590016752 |

**Program:** B.Tech CSE (Cybersecurity & Forensics)

---

## 📌 About the Project

The Password Vault is a local, file-based credential manager that lets users store, view, and delete website login credentials through an intuitive Java Swing GUI. Passwords are masked in the table display and all data persists to a local `passwords.txt` file — so your entries survive restarts.

---

## ✨ Features

- **Add Credentials** — Enter a website URL, username, and password to save an entry
- **Delete Entries** — Select any row and remove it with one click
- **Save to File** — Manually trigger a save to `passwords.txt` for persistent storage
- **Password Masking** — Passwords display as `********` in the table for privacy
- **Auto-Load** — Saved entries are loaded back automatically on startup

---

## 🛠️ Tech Stack

| Component | Details |
|-----------|---------|
| Language | Java 17+ |
| GUI Framework | Java Swing / AWT |
| Data Storage | Local flat file (`passwords.txt`) |
| File I/O | `PrintWriter`, `BufferedReader`, `FileReader` |
| Table Model | `DefaultTableModel` (JTable) |
| IDE | VS Code / IntelliJ IDEA |
| Version Control | GitHub |

---

## 🏗️ Architecture Overview

```
PasswordVault (main class)
│
├── init()              → Sets up JFrame, panels, labels, input fields, buttons
├── addEntry()          → Reads input fields, creates Entry object, adds to table
├── deleteEntry()       → Removes selected row from the JTable
├── saveFile()          → Writes all entries to passwords.txt via PrintWriter
├── loadFile()          → Reads passwords.txt at startup, populates the table
│
└── Entry (inner class)
    ├── website  : String
    ├── username : String
    └── password : String
```

**UI Layout:** `BorderLayout` — input panel at `NORTH`, scrollable `JTable` at `CENTER`, button panel in between.

---

## 🚀 How to Run

### Prerequisites
- Java JDK 17 or higher installed
- Terminal / Command Prompt

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/YOUR_USERNAME/password-vault.git
cd password-vault

# 2. Compile the source file
javac PasswordVault.java

# 3. Run the application
java PasswordVault
```

The application window will open. No additional setup or database required.

---

## 📖 Usage Guide

1. **Add a credential** — Fill in the Website, Username, and Password fields → click **Add**
2. **Save your data** — Click **Save** to write entries to `passwords.txt`
3. **Delete an entry** — Click on any row in the table → click **Delete**
4. **Restart safely** — Entries reload automatically from `passwords.txt` on next launch

---

## 📁 Project Structure

```
password-vault/
│
├── PasswordVault.java      # Main source file (all logic + GUI)
├── passwords.txt           # Auto-generated data file (created on first save)
└── README.md
```

---

## 🔮 Future Enhancements

- 🔒 **Encrypted storage** using Java Cryptography Architecture (JCA/JCE)
- 🔍 **Search/filter** functionality for quick credential lookup
- 📤 **Export / Import** for data backup and transfer
- 🔑 **Master password** login screen for access control
- 🗄️ **MySQL/SQLite backend** to replace flat-file storage

---

## ⚠️ Disclaimer

This project is built for educational purposes. Passwords are currently stored in **plain text**. Do not use this to store real or sensitive credentials without implementing encryption first.

---

*Submitted as Capstone Project — May 2026*
