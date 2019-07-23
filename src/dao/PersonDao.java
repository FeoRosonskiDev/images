package dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import model.DatabaseConnection;
import model.Person;

public class PersonDao {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public List<Person> listAllPerson() 
	{
		List<Person> persons = new ArrayList<Person>();
		String sql = "select * from person";

		con = new DatabaseConnection().getCon();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			Person p;

			while (rs.next()) {
				p = new Person();
				p.setId(rs.getInt("id"));
				p.setNames(rs.getString("pname"));
				p.setPhoto(rs.getBinaryStream("photo"));
				persons.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return persons;
	}

	public void listPhoto(Integer id, HttpServletResponse response) 
	{
		String sql = "select photo from person where id = ?";

		con = new DatabaseConnection().getCon();

		response.setContentType("image/*");

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				bis = new BufferedInputStream(rs.getBinaryStream("photo"));
				bos = new BufferedOutputStream(response.getOutputStream());
			}
			
			int bytes = 0;

			while ((bytes = bis.read()) != -1) {
				bos.write(bytes);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		
	}

	public boolean addPerson(Person person) 
	{
		boolean added = false;
		String sql = "insert into person (pname, photo) values (?,?)";

		con = new DatabaseConnection().getCon();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, person.getNames());
			ps.setBlob(2, person.getPhoto());

			if (ps.executeUpdate() == 1) {
				added = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return added;
	}

}
