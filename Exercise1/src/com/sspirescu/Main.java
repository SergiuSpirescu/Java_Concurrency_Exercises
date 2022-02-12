package com.sspirescu;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        Tutor tutor = new Tutor();
        Student student = new Student(tutor);
        tutor.setStudent(student);

        Thread tutorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                tutor.studyTime();
            }
        });

        Thread studentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                student.handInAssignment();
            }
        });

        tutorThread.start();
        studentThread.start();
    }
}

class Tutor {
    private Student student;
    private Lock lock;

    public synchronized void setStudent(Student student) {
        this.student = student;
        this.lock = new ReentrantLock();
    }

    public void studyTime() {
        System.out.println("Tutor has arrived");
        try {
            if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {

                try {
                    // wait for student to arrive and hand in assignment
                    Thread.sleep(300);
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Cannot get lock!");
            }
        }
        catch (InterruptedException e) {
           e.printStackTrace();
        }
        student.startStudy();
        System.out.println("Tutor is studying with student");
    }

    public void getProgressReport() {
        // get progress report
        System.out.println("Tutor gave progress report");
    }
}

class Student {

    private Tutor tutor;
    private Lock lock;

    Student(Tutor tutor) {
        this.tutor = tutor;
        this.lock = new ReentrantLock();
    }

    public void startStudy() {
        // study
        System.out.println("Student is studying");
    }

    public void handInAssignment() {

        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
              try {
                tutor.getProgressReport();
                System.out.println("Student handed in assignment");
            } finally {
                  lock.unlock();
              }
            } else {
                System.out.println("Cannot get lock!");
            }
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }
}