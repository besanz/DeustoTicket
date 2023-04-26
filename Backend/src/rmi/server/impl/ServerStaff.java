package rmi.server.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import rmi.server.api.IStaffService;
import rmi.server.exceptions.InvalidUser;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class ServerStaff extends UnicastRemoteObject implements IStaffService {

   
}
