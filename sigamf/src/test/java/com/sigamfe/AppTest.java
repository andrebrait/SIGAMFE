package com.sigamfe;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Unit test for simple App.
 */
@RunWith(Suite.class)
@SuiteClasses({ MaterialBusinessTest.class, UsuarioBusinessTest.class, ClientePFBusinessTest.class,
		ClientePJBusinessTest.class })
public class AppTest {

}
