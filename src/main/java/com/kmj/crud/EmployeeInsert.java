package com.kmj.crud;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.kmj.common.JDBCConnect.close;
import static com.kmj.common.JDBCConnect.getConnection;

public class EmployeeInsert {

    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;

        Scanner sc = new Scanner(System.in);

        System.out.println("추가할 사원의 개인정보를 입력하세요.");
        System.out.print("사원 ID: ");
        String empId = sc.nextLine();
        System.out.print("이름: ");
        String empName = sc.nextLine();
        System.out.print("주민등록번호: ");
        String empNo = sc.nextLine();
        System.out.print("e-mail: ");
        String email = sc.nextLine();
        System.out.print("Tel: ");
        String phone = sc.nextLine();
        System.out.print("부서번호: ");
        String deptCode = sc.nextLine();
        System.out.print("직급번호: ");
        String jobCode = sc.nextLine();
        System.out.print("연봉단계: ");
        String salLevel = sc.nextLine();

        int result = 0;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/kmj/mapper/tableQuery.xml"));
            String query = prop.getProperty("insertEmployee");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);
            pstmt.setString(2, empName);
            pstmt.setString(3, empNo);
            pstmt.setString(4, email);
            pstmt.setString(5, phone);
            pstmt.setString(6, deptCode);
            pstmt.setString(7, jobCode);
            pstmt.setString(8, salLevel);
            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sc.close();
            close(pstmt);
            close(con);
        }
        System.out.println(result + "명 개인정보 추가 완료");
    }
}
