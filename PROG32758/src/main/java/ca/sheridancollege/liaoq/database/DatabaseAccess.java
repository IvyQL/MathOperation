package ca.sheridancollege.liaoq.database;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.liaoq.beans.Calculation;


@Repository
public class DatabaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;	
	
	public Calculation getList(Integer id) {
		String q = "Select id, action, val1, val2, res, correct from my_results where id = :id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		ArrayList<Calculation> calculations = 
				(ArrayList<Calculation>)jdbc.query(q, namedParameters,
						new BeanPropertyRowMapper<Calculation>(Calculation.class));
		
		if(calculations.size() > 0) return calculations.get(0);
		else return new Calculation();				
		//else return new Calculation(1, "wrong", 6.0, 6.0, 6.0, false);				
		}
	
	public ArrayList<Calculation> getList1(){
		String q = "SELECT id,action, val1, val2, res, correct FROM my_results";
		ArrayList<Calculation> calculations =
				(ArrayList<Calculation>)jdbc.query(q,
						new  BeanPropertyRowMapper<Calculation>(Calculation.class));	
			return calculations;
	}	
	public void deleteCalc(Integer id) {
		MapSqlParameterSource d = new MapSqlParameterSource();
		String query = "DELETE FROM my_results WHERE id = :id";
		d.addValue("id", id);
		jdbc.update(query, d);
	}	
	public void addCalc(Calculation calculation) {
		MapSqlParameterSource d = new MapSqlParameterSource();
		d.addValue("action", calculation.getAction());
		d.addValue("val1", calculation.getVal1());
		d.addValue("val2", calculation.getVal2());
		d.addValue("res", calculation.getRes());
		d.addValue("correct", calculation.checkResult());//show true false
		String q = "INSERT INTO my_results (action, val1,val2,res,correct) VALUES (:action, :val1,:val2,:res,:correct)";
		jdbc.update(q, d);
		}	
	public int countRes() {
		String q = "SELECT COUNT(*) FROM my_results";
		int resu = jdbc.getJdbcTemplate().queryForObject(q, Integer.class);
		return resu;
	}
	
	public int getCorrectRes() {
		String q = "SELECT COUNT(*) FROM my_results WHERE correct=true";
		Float pc = jdbc.getJdbcTemplate().queryForObject(q, Float.class);
		return (int) ((pc/countRes()) *100);
	}
	
	public int getIncorrectRes() {
		String q = "SELECT COUNT(*) FROM my_results WHERE correct=false";
		Float pi = jdbc.getJdbcTemplate().queryForObject(q, Float.class);
		return (int) ((pi/countRes()) *100);
	}
		
		
		
		
		//MapSqlParameterSource d = new MapSqlParameterSource();
//		String query = "SELECT count(correct) FROM my_results Where correct = true";
//			
//		String q = "SELECT count(correct) FROM my_results";
//		int cor = jdbc.update(query, d);
//		
//		 String cc = "SELECT CAST(COUNT(CASE WHEN correct = TRUE THEN 1 END) as NUMBER(8,2)) / COUNT(correct) as PC FROM my_results";
//		 String ff = "SELECT CAST(COUNT(CASE WHEN correct = FALSE THEN 1 END) as NUMBER(8,2)) / COUNT(correct) as PI FROM my_results";
//		 	 
//		 Integer total = Integer.parseInt(cc) + Integer.parseInt(ff);
//		 Integer c = Integer.parseInt(cc)/total;	 
		
		 
//		
//		rom Fiona Li-Duong to everyone:    10:27 AM
//		SELECT COUNT(column_name)
//		FROM table_name
//		WHERE condition;
//		from Fiona Li-Duong to everyone:    10:42 AM
//		int rowsAffected = jdbc.update(query, namedParameters);
//		from Fiona Li-Duong to everyone:    10:46 AM
//		2 methods 
//		from Fiona Li-Duong to everyone:    10:46 AM
//		1. getCorrectPercent
//		from Fiona Li-Duong to everyone:    10:46 AM
//		2. getFalsePercent
//		from Fiona Li-Duong to everyone:    10:46 AM
//		two SQL queries that count - ONE counts the total number of answers and TWO counts the total number of false/ trues
//		from Fiona Li-Duong to everyone:    10:46 AM
//		store that into an integer 
//		from Fiona Li-Duong to everyone:    10:47 AM
//		use those integers to perform a calculation to determine the percentage
////		";
//	}

}

