package by.bundesliga.fan.controller.command.impl;

import by.bundesliga.fan.controller.command.Command;
import by.bundesliga.fan.service.exception.*;
import by.bundesliga.fan.service.UserService;
import by.bundesliga.fan.service.factory.ServiceFactory;
import by.bundesliga.fan.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrationCommand implements Command {

    private static final String REQUEST_PARAMETER_LOGIN = "login";
    private static final String REQUEST_PARAMETER_PASSWORD = "password";
    private static final String REQUEST_PARAMETER_FIRSTNAME = "firstname";
    private static final String REQUEST_PARAMETER_LASTNAME = "lastname";
    private static final String REQUEST_PARAMETER_ADDRESS = "address";
    private static final String REQUEST_PARAMETER_EMAIL = "email";
    private static final String REQUEST_PARAMETER_PHONE = "phone";
    private static final String REQUEST_PARAMETER_FAVTEAM = "favteam";
    private static final String REDIRECT_COMMAND_SUCCESS = "Controller?command=go_to_main&register=success";
    private static final String REDIRECT_COMMAND_ERROR = "Controller?command=go_to_register&register=error";
    private static final String REDIRECT_COMMAND_ERROR_DUPLICATE = "Controller?command=go_to_register&error=unique";
    private static final int DEFAULT_ROLE_ID = 2;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String login = req.getParameter(REQUEST_PARAMETER_LOGIN);
        String password = req.getParameter(REQUEST_PARAMETER_PASSWORD);
        String firstname = req.getParameter(REQUEST_PARAMETER_FIRSTNAME);
        String lastname = req.getParameter(REQUEST_PARAMETER_LASTNAME);
        String email = req.getParameter(REQUEST_PARAMETER_EMAIL);
        String phone = req.getParameter(REQUEST_PARAMETER_PHONE);
        String favTeam = req.getParameter(REQUEST_PARAMETER_FAVTEAM);
        String address = req.getParameter(REQUEST_PARAMETER_ADDRESS);

        // Default role for user;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        boolean registrationResult;

        try {
            registrationResult = userService.signUp(login, password.getBytes(), firstname, lastname, address, email, phone, favTeam, DEFAULT_ROLE_ID);

            if (registrationResult) {
                resp.sendRedirect(REDIRECT_COMMAND_SUCCESS);
            } else {
                resp.sendRedirect(REDIRECT_COMMAND_ERROR);
            }
        } catch (ServiceUserAlreadyExistsException e) {
            resp.sendRedirect(REDIRECT_COMMAND_ERROR_DUPLICATE);
        } catch (ServiceException e) {
            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
        }
    }
}
