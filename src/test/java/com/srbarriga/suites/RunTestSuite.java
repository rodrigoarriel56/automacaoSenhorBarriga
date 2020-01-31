/**
 * Suite de teste para o pacote web
 * @author Rodrigo Arriel
 */

package com.srbarriga.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.srbarriga.web.CadastroConta;
import com.srbarriga.web.Login;

@RunWith(Suite.class)
@SuiteClasses(
        {
        	//NovoUsuario.class,
            Login.class,
            CadastroConta.class,
            //ContaExistente.class
        }
)
public class RunTestSuite {

}
