package com.kmj.crud;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;


import static com.kmj.common.JDBCConnect.close;
import static com.kmj.common.JDBCConnect.getConnection;

public class EmployeeSelect {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/kmj/mapper/tableQuery.xml"));

            Scanner sc = new Scanner(System.in);
            System.out.print("조회할 부서의 부서 코드를 입력하세요.");
            String deptCode = sc.nextLine();

            String query = prop.getProperty("selectEmpByDeptCode");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, deptCode);

            rset = pstmt.executeQuery();
            System.out.println(deptCode + "번 부서 직원 목록은 다음과 같습니다.");
            while(rset.next()) {
                String empName = rset.getString("EMP_NAME");
                String empId = rset.getString("EMP_ID");
                String email = rset.getString("EMAIL");

                System.out.println("ID: " + empId + " / 이름: " + empName + " / Contact: " + email);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }

    }
    }
