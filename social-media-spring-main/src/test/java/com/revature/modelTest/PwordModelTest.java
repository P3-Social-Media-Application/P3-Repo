package com.revature.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.models.PwordModel;

@SpringBootTest(classes = PwordModel.class)
public class PwordModelTest {
	
	@Test
	void PwordModelToStringTest() {
		PwordModel pchange = new PwordModel("password", "password1");
		
		assertEquals(pchange.toString(), "PwordModel [oldPass=" + pchange.getOldPass() + ", newPass=" + pchange.getNewPass() + "]");
	}
}
