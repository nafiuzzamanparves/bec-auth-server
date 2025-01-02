package com.bedatasolutions.authServer.json_test;

import com.bedatasolutions.authServer.usecase.auth.CustomUserDetails;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.*;

public class JsonObjectTest {

    String json = """
            {
              "@class": "java.util.Collections$UnmodifiableMap",
              "java.security.Principal": {
                "@class": "org.springframework.security.authentication.UsernamePasswordAuthenticationToken",
                "authorities": [
                  "java.util.Collections$UnmodifiableRandomAccessList",
                  [
                    {
                      "@class": "org.springframework.security.core.authority.SimpleGrantedAuthority",
                      "authority": "ADMIN"
                    },
                    {
                      "@class": "org.springframework.security.core.authority.SimpleGrantedAuthority",
                      "authority": "DEV"
                    },
                    {
                      "@class": "org.springframework.security.core.authority.SimpleGrantedAuthority",
                      "authority": "USER"
                    }
                  ]
                ],
                "details": {
                  "@class": "org.springframework.security.web.authentication.WebAuthenticationDetails",
                  "remoteAddress": "127.0.0.1",
                  "sessionId": "05023C6F28C701EE2B94E41FD2291FE0"
                },
                "authenticated": true,
                "principal": {
                  "@class": "com.bedatasolutions.authServer.service.CustomUserDetails",
                  "user": {
                    "@class": "com.bedatasolutions.authServer.dao.UserDao",
                    "id": 1,
                    "fullName": "admin",
                    "email": "nafiuzzaman.parves@gmail.com",
                    "phone": "3451432",
                    "age": 29,
                    "address": "Dhaka",
                    "password": "{noop}admin",
                    "createdAt": [
                      "java.sql.Timestamp",
                      1735207285000
                    ],
                    "updatedAt": [
                      "java.sql.Timestamp",
                      1735207285000
                    ],
                    "isAccountNonExpired": true,
                    "isAccountNonLocked": true,
                    "isCredentialsNonExpired": true,
                    "enabled": true,
                    "isRoleResourceAccess": true,
                    "mfaSecret": null,
                    "mfaKeyId": "test id",
                    "mfaEnabled": true,
                    "mfaRegistered": true,
                    "roles": [
                      "org.hibernate.collection.spi.PersistentSet",
                      []
                    ],
                    "resources": [
                      "org.hibernate.collection.spi.PersistentSet",
                      []
                    ],
                    "authorities": [
                      "java.util.ArrayList",
                      [
                        {
                          "@class": "org.springframework.security.core.authority.SimpleGrantedAuthority",
                          "authority": "ADMIN"
                        },
                        {
                          "@class": "org.springframework.security.core.authority.SimpleGrantedAuthority",
                          "authority": "DEV"
                        },
                        {
                          "@class": "org.springframework.security.core.authority.SimpleGrantedAuthority",
                          "authority": "USER"
                        }
                      ]
                    ]
                  },
                  "enabled": true,
                  "password": "{noop}admin",
                  "accountNonLocked": true,
                  "accountNonExpired": true,
                  "credentialsNonExpired": true,
                  "authorities": [
                    "java.util.ArrayList",
                    [
                      {
                        "@class": "org.springframework.security.core.authority.SimpleGrantedAuthority",
                        "authority": "ADMIN"
                      },
                      {
                        "@class": "org.springframework.security.core.authority.SimpleGrantedAuthority",
                        "authority": "DEV"
                      },
                      {
                        "@class": "org.springframework.security.core.authority.SimpleGrantedAuthority",
                        "authority": "USER"
                      }
                    ]
                  ],
                  "username": "admin"
                },
                "credentials": null
              },
              "org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest": {
                "@class": "org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest",
                "authorizationUri": "http://127.0.0.1:8081/oauth2/authorize",
                "authorizationGrantType": {
                  "value": "authorization_code"
                },
                "responseType": {
                  "value": "code"
                },
                "clientId": "oidc-client",
                "redirectUri": "http://localhost:8080/login/oauth2/code/oidc-client",
                "scopes": [
                  "java.util.Collections$UnmodifiableSet",
                  [
                    "openid",
                    "profile"
                  ]
                ],
                "state": null,
                "additionalParameters": {
                  "@class": "java.util.Collections$UnmodifiableMap",
                  "continue": ""
                },
                "authorizationRequestUri": "http://127.0.0.1:8081/oauth2/authorize?response_type=code&client_id=oidc-client&scope=openid%20profile&redirect_uri=http://localhost:8080/login/oauth2/code/oidc-client&continue=",
                "attributes": {
                  "@class": "java.util.Collections$UnmodifiableMap"
                }
              }
            }""";

    @Test
    public void testJsonObject() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper().enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        om.enable(JsonParser.Feature.INCLUDE_SOURCE_IN_LOCATION);
        // Map<String, Object> output = om.readValue(json, new TypeReference<>() {
        // });
        CustomUserDetails output = om.readValue(json, CustomUserDetails.class);
        System.out.println(json);
    }

    @Test
    public void testJsonObject2() throws JsonProcessingException {
        Employee emp = new Employee("Alice", 123);
        String filename = "employee.txt";

        // Serialize (store object to disk)
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(emp);
            System.out.println("Object serialized and saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize (read object from disk)
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Employee deserializedEmp = (Employee) ois.readObject();
            System.out.println("Object deserialized: " + deserializedEmp);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}