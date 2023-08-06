package com.driver.test;

import com.driver.Gmail;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestCases {

    private Gmail gmail;

    @Before
    public void setUp() {
        // Initialize the Gmail object with a test email ID and inbox capacity
        gmail = new Gmail("test@gmail.com", 3);
    }

    @Test
    public void testReceiveMail() {
        // Test the receiveMail() method
        try {
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("21/12/2022");
            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("22/12/2022");
            Date date3 = new SimpleDateFormat("dd/MM/yyyy").parse("23/12/2022");

            gmail.receiveMail(date1, "Sender1", "Message1");
            gmail.receiveMail(date2, "Sender2", "Message2");
            gmail.receiveMail(date3, "Sender3", "Message3");

            // The inbox should be full now
            assertEquals(3, gmail.getInboxSize());

            // Receive one more mail, the oldest mail should move to trash
            Date date4 = new SimpleDateFormat("dd/MM/yyyy").parse("24/12/2022");
            gmail.receiveMail(date4, "Sender4", "Message4");

            assertEquals(3, gmail.getInboxSize()); // Inbox size should still be 3
            assertEquals(1, gmail.getTrashSize()); // Trash size should be 1

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteMail() {
        // Test the deleteMail() method
        try {
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("21/12/2022");
            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("22/12/2022");

            gmail.receiveMail(date1, "Sender1", "Message1");
            gmail.receiveMail(date2, "Sender2", "Message2");

            assertEquals(2, gmail.getInboxSize());

            // Delete a mail that exists in the inbox
            gmail.deleteMail("Message1");
            assertEquals(1, gmail.getInboxSize());

            // Try deleting a mail that doesn't exist in the inbox
            gmail.deleteMail("NonExistentMessage");
            assertEquals(1, gmail.getInboxSize()); // Inbox size should remain unchanged

        } catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testFindLatestMessage() {
        // Test the findLatestMessage() method
        try {
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("21/12/2022");
            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("22/12/2022");

            gmail.receiveMail(date1, "Sender1", "Message1");
            gmail.receiveMail(date2, "Sender2", "Message2");

            // The latest message should be "Message2"
            assertEquals("Message2", gmail.findLatestMessage());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindOldestMessage() {
        // Test the findOldestMessage() method
        try {
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("21/12/2022");
            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("22/12/2022");

            gmail.receiveMail(date1, "Sender1", "Message1");
            gmail.receiveMail(date2, "Sender2", "Message2");

            // The oldest message should be "Message1"
            assertEquals("Message1", gmail.findOldestMessage());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // Add more test methods to cover other functionalities of the Gmail class

}
