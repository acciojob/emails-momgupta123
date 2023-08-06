package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId) {
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword) {
        // Change password only if the oldPassword is equal to the current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character

        // Check if the oldPassword matches the current password
        if (oldPassword.equals(password)) {
            // Check if the newPassword meets all the conditions
            if (newPassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
                this.password = newPassword;
                System.out.println("Password changed successfully!");
            } else {
                System.out.println("Invalid new password! Please make sure it meets all the conditions.");
            }
        } else {
            System.out.println("Incorrect old password! Password not changed.");
        }
    }
}
