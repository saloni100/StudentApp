package com.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyGenerator {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.salonig.studentdemo.db"); // Your app package name and the (.db) is the folder where the DAO files will be generated into.
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        addUserEntities(schema);
       addStudentEntities(schema);
    }



    // This is use to describe the colums of your table
    private static Entity addStudentEntities(final Schema schema) {
        Entity student = schema.addEntity("Student");
        student.addIdProperty().primaryKey().autoincrement();
        student.addIntProperty("address_position");
        student.addStringProperty("roll_no");
        student.addStringProperty("full_name");
        student.addDateProperty("date");
        student.addBooleanProperty("gender");
        student.addIntProperty("course_position");
        student.addStringProperty("course_name");
        student.addStringProperty("image_path");
        student.addStringProperty("address_name");
        return student;
    }




    // This is use to describe the colums of your table
    private static Entity addUserEntities(final Schema schema) {
        Entity user = schema.addEntity("User");
        user.addIdProperty().primaryKey().autoincrement();
        user.addIntProperty("user_id").notNull();
        user.addStringProperty("last_name");
        user.addStringProperty("first_name");
        user.addStringProperty("email");
        return user;
    }


}