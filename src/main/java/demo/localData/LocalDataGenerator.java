package demo.localData;

import demo.common.PropertiesReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by thibautvirolle on 12/10/15.
 */
@Controller
public class LocalDataGenerator {

    private static final Logger log = LoggerFactory.getLogger(LocalDataGenerator.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/create_default_db", method = RequestMethod.GET)
    public String createDefaultDB(Model model) {

        DataSource dataSource = jdbcTemplate.getDataSource();

        PropertiesReader pr = new PropertiesReader();
        String databaseDriver = "";
        try {
            databaseDriver = pr.getPropValues("spring.datasource.driverClassName");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (databaseDriver.equals("org.h2.Driver")) {
            Resource resource = new ClassPathResource("static/sql/create.sql");
            ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
            databasePopulator.execute(dataSource);

            resource = new ClassPathResource("static/sql/populate.sql");
            databasePopulator = new ResourceDatabasePopulator(resource);
            databasePopulator.execute(dataSource);

            model.addAttribute("database_creation", "Base de données créée.");
        } else {
            model.addAttribute("database_creation", "Vous n'avez pas le droit d'accéder à cette fonction.");
        }

        return "local-data/database_creation";


    }

}
