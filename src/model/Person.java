package model;

import java.io.InputStream;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {

	private Integer id;
	private String names;
	private InputStream photo;
}
