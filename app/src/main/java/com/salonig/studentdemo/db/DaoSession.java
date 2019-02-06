package com.salonig.studentdemo.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.salonig.studentdemo.db.User;
import com.salonig.studentdemo.db.Student;

import com.salonig.studentdemo.db.UserDao;
import com.salonig.studentdemo.db.StudentDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig studentDaoConfig;

    private final UserDao userDao;
    private final StudentDao studentDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        studentDao = new StudentDao(studentDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(Student.class, studentDao);
    }
    
    public void clear() {
        userDaoConfig.clearIdentityScope();
        studentDaoConfig.clearIdentityScope();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

}