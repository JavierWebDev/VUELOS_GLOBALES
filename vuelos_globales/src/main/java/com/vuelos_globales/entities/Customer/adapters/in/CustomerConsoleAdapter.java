package com.vuelos_globales.entities.Customer.adapters.in;

import java.util.Scanner;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.List;

import com.vuelos_globales.entities.Customer.application.CustomerService;
import com.vuelos_globales.entities.Customer.domain.Customer;
import com.vuelos_globales.entities.DocumentType.domain.DocumentType;
import com.vuelos_globales.modules.ConsoleUtils;




public class CustomerConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    private final CustomerService customerService;

    public CustomerConsoleAdapter(CustomerService customerService) {
        this.customerService = customerService;
    }
    public void createCustomer() {
        String rta = "S";
    
        while (rta.equalsIgnoreCase("S")) {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR CLIENTE ***************");
            System.out.println("[*] INGRESE EL ID DEL CLIENTE A CREAR: ");
            String id = sc.nextLine();
    
            
            Optional<Customer> customer = customerService.getCustomerById(id);
            if (customer.isPresent()) {
                System.out.println(MessageFormat.format("[!] EL ID ({0}) YA ESTA OCUPADO.", id));
                ConsoleUtils.esperarEntrada();
                continue; 
            }
    
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR CLIENTE ***************");
            System.out.println("[*] INGRESE EL NOMBRE DEL CLIENTE: ");
            String customerName = sc.nextLine();
    
            System.out.println("[*] INGRESE EL APELLIDO DEL CLIENTE: ");
            String customerLastName = sc.nextLine();
    
            int customerAge = 0;
            boolean validAge = false;
            while (!validAge) {
                try {
                    System.out.println("[*] INGRESE LA EDAD DEL CLIENTE: ");
                    customerAge = Integer.parseInt(sc.nextLine().trim());
                    validAge = true;
                } catch (NumberFormatException e) {
                    System.out.println("[!] INGRESASTE UNA OPCION INVALIDA PARA EDAD DEL CLIENTE.");
                }
            }
    
            int idDocument = 0;
            boolean documentTypeCreated = false;
    
            while (!documentTypeCreated) {
                ConsoleUtils.limpiarConsola();
                try {
                    System.out.println("*************** REGISTRAR TIPO DE DOCUMENTO ***************");
                    System.out.println("[*] INGRESE EL ID DEL TIPO DE DOCUMENTO A CREAR: ");
                    idDocument = Integer.parseInt(sc.nextLine().trim());
    
                    Optional<DocumentType> documentType = customerService.getDocumentTypeById(idDocument);
                    if (documentType.isPresent()) {
                        System.out.println(MessageFormat.format("[!] EL ID ({0}) YA ESTA OCUPADO.", idDocument));
                        ConsoleUtils.esperarEntrada();
                        continue;
                    }
    
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** REGISTRAR TIPO DE DOCUMENTO ***************");
                    System.out.println("[*] INGRESE EL NOMBRE DEL TIPO DE DOCUMENTO: ");
                    String docTypeName = sc.nextLine();
    
                    DocumentType newDocumentType = new DocumentType(idDocument, docTypeName);
                    customerService.createDocumentType(newDocumentType);
                    documentTypeCreated = true;
                } catch (NumberFormatException e) {
                    System.out.println("[!] INGRESASTE UNA OPCION INVALIDA PARA ID DE DOCUMENTO.");
                    ConsoleUtils.esperarEntrada();
                }
            }
    
            Customer newCustomer = new Customer(id, customerName, customerLastName, customerAge, idDocument);
            customerService.createCustomer(newCustomer);
    
            System.out.println("[?] DESEA AÑADIR OTRO CLIENTE? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine();
        }
    }
    


    public void searchCustomer() {
        List<Customer> customers = customerService.getAllCustomers();
            
        if (customers.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN CLIENTE REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** BUSCAR CLIENTE ***************");
            System.out.println("[?] INGRESE EL ID DEL CLIENTE A BUSCAR: ");
            String findId = sc.nextLine();
    
            Optional<Customer> customer = customerService.getCustomerById(findId);
            customer.ifPresentOrElse(
                c -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** CLIENTE ***************");
                    
                    int idDocumentType = c.getIdDocumentType();
                    System.out.println(MessageFormat.format("[*] ID : {0}\n[*] NOMBRE : {1}\n[*]APELLIDO : {2}\n[*] EDAD: {3}\n[*] DOCUMENTO ID: {4}", c.getId(), c.getName(), c.getLastName(), c.getAge(), idDocumentType));
                    ConsoleUtils.esperarEntrada();
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!]  CLIENTE NO ENCONTRADO");
                    ConsoleUtils.esperarEntrada();
                });
                ConsoleUtils.limpiarConsola();
                System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                sc.nextLine();
        }
    }
    

    public void updateCustomer() {
        List<Customer> customers = customerService.getAllCustomers();
    
        if (customers.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN CLIENTE REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[?] INGRESE EL ID DEL CLIENTE A BUSCAR: ");
            String findId = sc.nextLine();
    
            Optional<Customer> customer = customerService.getCustomerById(findId);
            customer.ifPresentOrElse(
                c -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** ACTUALIZAR CLIENTE ***************");
                    System.out.println(MessageFormat.format("[*] ID : {0}\n[*] NOMBRE : {1}\n[*]APELLIDO : {2}\n[*] EDAD: {3}\n[*] DOCUMENTO ID: {4}", c.getId(), c.getName(), c.getLastName(), c.getAge(), c.getIdDocumentType()));
    
                    System.out.println("[?] INGRESE EL NUEVO NOMBRE DEL CLIENTE: ");
                    String updateName = sc.nextLine();
    
                    System.out.println("[?] INGRESE EL NUEVO APELLIDO DEL CLIENTE: ");
                    String updateLastName = sc.nextLine();
    
                    int updateAge = 0;
                    boolean validAge = false;
                    while (!validAge) {
                        try {
                            System.out.println("[?] INGRESE LA NUEVA EDAD DEL CLIENTE: ");
                            updateAge = Integer.parseInt(sc.nextLine().trim());
                            validAge = true;
                        } catch (NumberFormatException e) {
                            System.out.println("[!] INGRESASTE UNA OPCION INVALIDA PARA EDAD DEL CLIENTE.");
                        }
                    }
    
                    int updateCustomerClient = 0;
                    boolean documentType = false;
                    while (!documentType) {
                        try {
                            System.out.println("[?] INGRESE EL NUEVO TIPO DE DOCUMENTO DEL CLIENTE: ");
                            updateCustomerClient = Integer.parseInt(sc.nextLine().trim());
                            documentType = true;
                        } catch (NumberFormatException e) {
                            System.out.println("[!] INGRESASTE UNA OPCION INVALIDA PARA TIPO DE DOCUMENTO.");
                        }
                    }
                    Customer updatedCustomer = new Customer(c.getId(), updateName, updateLastName, updateAge, updateCustomerClient);
                    customerService.updateCustomer(updatedCustomer);
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[*] CLIENTE ACTUALIZADO CORRECTAMENTE.");
                    sc.nextLine(); 
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!] CLIENTE NO ENCONTRADO");
                    sc.nextLine(); 
                }
            );
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine(); 
        }
    }
    

    public void deleteCustomer() {
        List<Customer> customers = customerService.getAllCustomers();
        
        if (customers.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN CLIENTE REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[?] INGRESE EL ID DEL CLIENTE A ELIMINAR: ");
            String findId = sc.nextLine();

            Optional<Customer> customer = customerService.getCustomerById(findId);
            customer.ifPresentOrElse(
                c -> {
                    customerService.deleteCustomer(findId);
                    System.out.println("[!] CLIENTE ELIMINADO CORRECTAMENTE.");
                    sc.nextLine();
                },
                () -> {
                    System.out.println("[!]  CLIENTE NO ENCONTRADO");
                }
            );
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }

    public void getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        
        if (customers.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN CLIENTE REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            customerService.getAllCustomers().forEach(c -> {
               System.out.println(MessageFormat.format("[*] ID : {0}\n[*] NOMBRE : {1}\n[*] EDAD: {2}\n[*] DOCUMENTO: {3}", c.getId(), c.getName() + c.getLastName(), c.getIdDocumentType())); 
            });
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }
}
