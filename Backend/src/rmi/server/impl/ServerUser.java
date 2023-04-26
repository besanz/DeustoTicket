package rmi.server.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data.entidades.Artista;
import data.entidades.Cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import rmi.server.api.IUserService;
import rmi.server.exceptions.InvalidUser;

public class ServerUser extends UnicastRemoteObject implements IUserService {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
            System.exit(0);
        }
/* 
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
*/
        String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

        try {
            IUserService objServer = new ServerUser();
            Registry registry = LocateRegistry.createRegistry((Integer.valueOf(args[1])));
            registry.rebind(name, objServer);
            System.out.println("* Server '" + name + "' active and waiting...");
        } catch (Exception e) {
            System.err.println("- Exception running the server: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
