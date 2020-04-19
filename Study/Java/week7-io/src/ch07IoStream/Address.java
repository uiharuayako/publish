package ch07IoStream;

import java.io.Serializable;

public class Address implements Serializable {
	protected String first, email;

	public Address() {
		first = email = "";
	}

	public Address(String _first, String _email) {
		first = _first;
		email = _email;
	}

	public String toString() {
		return first + " (" + email + ")";
	}
}
