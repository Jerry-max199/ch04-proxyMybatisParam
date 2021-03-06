package com.zzjm;

import com.zzjm.dao.StudentDao;
import com.zzjm.domain.Student;
import org.apache.ibatis.session.SqlSession;
import com.zzjm.Utils.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTest {
    @Test
    public void ProxyselelctStudent() {
        /**
         * 使用mybatis的动态代理机制，使用sqlSession.getMapper(Dao接口)
         * getMapper能够获取dao接口对于实现类的对象。
         */
        SqlSession sqlSession = MyUtils.getSqlsession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        Student student = studentDao.selectStudent(1001);
        System.out.println(student);
        sqlSession.close();
    }

    @Test
    public void selectMultiParamTest() {
        SqlSession sqlSession = MyUtils.getSqlsession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = studentDao.selectMulitParam("盾山", 20);
        for (Student student : studentList) {
            System.out.println("学生" + student);
        }
        sqlSession.close();
    }

    @Test

    public void selectStudentObjectTest() {
    SqlSession sqlSession = MyUtils.getSqlsession();
    StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
    Student student = new Student();
    student.setName("张三");
    student.setId(1001);
    List<Student> studentList=studentDao.selectStudentObject(student);
    for (Student student1:studentList){
        System.out.println("学生"+student1);
    }
}
    @Test
    public void selectStudentIndexTest(){
        SqlSession sqlSession=MyUtils.getSqlsession();
        StudentDao studentDao=sqlSession.getMapper(StudentDao.class);
        List<Student> studentList=studentDao.selectStudentIndex("盾山","dunshan.com");
        for (Student student:studentList){
            System.out.println(student);
        }
    }
    @Test
    public void SelectStudentMapTest(){
        SqlSession sqlSession=MyUtils.getSqlsession();
        StudentDao studentDao=sqlSession.getMapper(StudentDao.class);
        Map<String,Object> data=new HashMap<>();
        data.put("name","盾山");
        data.put("age",30);
        List<Student> studentList=studentDao.selectStudentMap(data);
        for (Student student:studentList){
            System.out.println(student);
        }
    }
}
