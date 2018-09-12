package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import model.Teacher;


public class TeacherDAO {
	private final static int fieldNum = 8;//教师信息的条目
	private final static int showNum = 8;//一页中显示的数目
	protected final static DBHelper db = DBHelper.getDBHelper();
	protected static ResultSet rs;

	// update
	public static boolean update(Teacher teacher) {
		boolean result = false;
		if (teacher == null) {
			return result;
		}
		try {
			// check
			if (queryBySno(teacher.getSno()) == 0) {
				return result;
			}
			// update
			String sql = "update teacher set sex=?,department=?,age=?,tel=?,wage=? where name=? and sno=?";
			String[] param = { teacher.getSex(), teacher.getDepartment(), teacher.getAge(), teacher.getTel(), teacher.getWage(),
					teacher.getName(), teacher.getSno() };
			int rowCount = db.executeUpdate(sql, param);
			if (rowCount == 1) {
				result = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}

	// delete
	public static boolean delete(Teacher teacher) {
		boolean result = false;
		if (teacher == null) {
			return result;
		}
		String sql = "delete from teacher where name=? and sno=?";
		String[] param = { teacher.getName(), teacher.getSno() };
		int rowCount = db.executeUpdate(sql, param);
		if (rowCount == 1) {
			result = true;
		}
		destroy();
		return result;
	}

	// add
	public static boolean add(Teacher teacher) {
		boolean result = false;
		if (teacher == null) {
			return result;
		}
		try {
			// check
			if (queryBySno(teacher.getSno()) == 1) {
				return result;
			}
			// insert
			String sql = "insert into teacher(sno,name,sex,age,department,wage,tel) values(?,?,?,?,?,?,?)";
			String[] param = { teacher.getSno(), teacher.getName(), teacher.getSex(), teacher.getAge(),teacher.getDepartment(),
					teacher.getWage(),teacher.getTel() };
			if (db.executeUpdate(sql, param) == 1) {
				result = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}

	// query by name
	public static  String[][] queryByName(String name) {
		String[][] result = null;
		if (name.length() < 0) {
			return result;
		}
		List<Teacher> teachers = new ArrayList<Teacher>();
		int i = 0;
		String sql = "select * from teacher where name like ?";
		String[] param = { "%" + name + "%" };
		rs = db.executeQuery(sql, param);
		try {
			while (rs.next()) {
				buildList(rs, teachers, i);
				i++;
			}
			if (teachers.size() > 0) {
				result = new String[teachers.size()][fieldNum];
				for (int j = 0; j < teachers.size(); j++) {
					buildResult(result, teachers, j);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}

		return result;
	}

	// query
	public static String[][] list(int pageNum) {
		String[][] result = null;
		if (pageNum < 1) {
			return result;
		}
		List<Teacher> teachers = new ArrayList<Teacher>();
		int i = 0;
		int beginNum = (pageNum - 1) * showNum;
		String sql = "select * from teacher limit ?,?";
		Integer[] param = { beginNum, showNum };
		rs = db.executeQuery(sql, param);
		try {
			while (rs.next()) {
				buildList(rs, teachers, i);
				i++;
			}
			if (teachers.size() > 0) {
				result = new String[teachers.size()][fieldNum];
				for (int j = 0; j < teachers.size(); j++) {
					buildResult(result, teachers, j);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}

	// 将rs记录添加到list中
	private static void buildList(ResultSet rs, List<Teacher> list, int i) throws SQLException {
		Teacher teacher = new Teacher();
		teacher.setId(i + 1);
		teacher.setSno(rs.getString("sno"));
		teacher.setName(rs.getString("name"));
		teacher.setSex(rs.getString("sex"));
		teacher.setAge(rs.getString("age"));
		teacher.setDepartment(rs.getString("department"));
		teacher.setWage(rs.getString("wage"));
		teacher.setTel(rs.getString("tel"));
		list.add(teacher);
	}

	// 将list中记录添加到二维数组中
	private static void buildResult(String[][] result, List<Teacher> teachers, int j) {
		Teacher teacher= teachers.get(j);
		result[j][0] = String.valueOf(teacher.getId());
		result[j][1] = teacher.getName();
		result[j][2] = teacher.getSno();
		result[j][3] = teacher.getSex();
		result[j][4] = teacher.getAge();
		result[j][5] = teacher.getDepartment();
		result[j][6] = teacher.getWage();
		result[j][7] = teacher.getTel();
	}

	// query by sno
	public static int queryBySno(String sno) throws SQLException {
		int result = 0;
		if ("".equals(sno) || sno == null) {
			return result;
		}
		String checkSql = "select * from teacher where sno=?";
		String[] checkParam = { sno };
		rs = db.executeQuery(checkSql, checkParam);
		if (rs.next()) {
			result = 1;
		}
		return result;
	}
	protected static void destroy() {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			db.close();
		}
	}
}

