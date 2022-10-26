package ca.sheridancollege.liaoq.beans;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calculation implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String action;
	private Double val1, val2, res;
	private Boolean correct;
	
	public Boolean checkResult() {
		switch(action) {
		case "add":
			if(val1+val2 == res)
				correct = true;
			else 
				correct = false;
			break;
		
		case "abstract":
			if(val1-val2 == res)
				correct = true;
			else
				correct = false;
			break;
			
		case "multiply":
			if(val1 * val2 == res)
				correct = true;
			else
				correct = false;
			break;
			
		case "divide":
			if(val1 / val2 == res)
				correct = true;
			else
				correct = false;
			break;
		}
		return correct;
	}
	}

	

