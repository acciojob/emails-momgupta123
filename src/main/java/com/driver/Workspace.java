package com.driver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Workspace extends Gmail {
    private ArrayList<Meeting> calendar;

    public Workspace(String emailId) {
        super(emailId, Integer.MAX_VALUE);
        calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting) {
        calendar.add(meeting);
    }

    public int findMaxMeetings() {
        int maxMeetings = 0;
        int currentMeetings = 0;
        Collections.sort(calendar, Comparator.comparing(Meeting::getStartTime));

        ArrayList<Meeting> meetingsToRemove = new ArrayList<>();
        for (int i = 0; i < calendar.size(); i++) {
            Meeting meeting = calendar.get(i);
            while (i + 1 < calendar.size() && meeting.getEndTime().isAfter(calendar.get(i + 1).getStartTime())) {
                currentMeetings++;
                meetingsToRemove.add(calendar.get(i + 1));
                i++;
            }
            if (currentMeetings > maxMeetings) {
                maxMeetings = currentMeetings;
            }
            currentMeetings--;
        }

        calendar.removeAll(meetingsToRemove);
        return maxMeetings;
    }
}
