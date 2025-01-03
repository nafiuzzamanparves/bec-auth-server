package com.bedatasolutions.authServer.entity.permission.exception;

public class PermissionNotFoundException extends Exception {

    public PermissionNotFoundException() {
        super("Permission not found");
    }

}