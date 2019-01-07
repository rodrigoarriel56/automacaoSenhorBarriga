package com.srbarriga.herokuapp.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.srbarriga.herokuapp.web.Login;
import com.srbarriga.herokuapp.web.NovoUsuario;
import com.srbarriga.herokuapp.web.ContaExistente;
import com.srbarriga.herokuapp.web.CadastroConta;

/**
 * Suite de teste para o pacote web
 * @author Fredi Roldan <fredi.roldan@yaman.com.br>
 */
@RunWith(Suite.class)
@SuiteClasses(
        {
        	NovoUsuario.class,
            Login.class,
            CadastroConta.class,
            ContaExistente.class
        }
)
public class WebTestSuite {

}
