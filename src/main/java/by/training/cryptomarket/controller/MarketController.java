package by.training.cryptomarket.controller;

import by.training.cryptomarket.command.Command;
import by.training.cryptomarket.command.CommandFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

@Controller
public class MarketController {


    /**
     * The field for storage a logger.
     */
    static final Logger LOGGER = LogManager.getLogger("by.training.final.ServletLogger");

    @Autowired
    ServletConfig config;

    @Autowired
    CommandFactory commandFactory;



    @PostConstruct
    public void init() {

        Properties props = new Properties();


        String version;
        String buildDate;

        try {

            props.load(config.getServletContext().getResourceAsStream("/WEB-INF/classes/build.properties"));
            version = props.getProperty("build.version");
            buildDate = props.getProperty("build.date");

        } catch (Exception e) {
            e.printStackTrace();
            version = "-";
            buildDate = "-";;
        }


        ServletContext servletContext = config.getServletContext();
        servletContext.setAttribute("buildVersion",version);
        servletContext.setAttribute("buildDate",buildDate);


        servletContext.setAttribute("loginmessage","");



    }





   // @RequestMapping(value={"/login","/market","/order","/wallet","/deposit","/withdraw"})

    //@RequestMapping(value={"/login","/market","/order","/wallet","/deposit","/withdraw","/registration"})
  //  @RequestMapping(value={"/**"})

    @RequestMapping(path = "/*")
    public String markethandler(ModelMap model, HttpServletRequest request,  HttpServletResponse response){

    /*    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.isAuthenticated());
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getDetails());
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getName());*/


           Command command;
        String commandName = request.getParameter("command");
      if (commandName == null){

          return "login";
      }

        String path;
        command = commandFactory.createCommand(commandName);

        try {
            path = command.execute(request, response,model);


        } catch (Exception e) {
            path = "error";
        }

        return path;
    }




    @RequestMapping(path = "/test*")
    public String testhandler(ModelMap model, HttpServletRequest request,  HttpServletResponse response){

        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");



        model.addAttribute("boollean",true);
        model.addAttribute("typeoforder","market");

        return "test";
    }






}