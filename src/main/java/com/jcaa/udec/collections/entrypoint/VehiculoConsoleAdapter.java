package com.jcaa.udec.collections.entrypoint;

import com.jcaa.udec.collections.adapter.persistence.memory.InMemoryVehiculoRepository;
import com.jcaa.udec.collections.application.service.VehiculoUseCase;
import com.jcaa.udec.collections.domain.core.model.Vehiculo;
import com.jcaa.udec.collections.domain.port.out.VehiculoOutputPort;

import java.util.List;
import java.util.Scanner;

public class VehiculoConsoleAdapter {

    private final VehiculoUseCase vehiculoUseCase;
    private final Scanner scanner;

    public VehiculoConsoleAdapter(VehiculoUseCase vehiculoUseCase) {
        this.vehiculoUseCase = vehiculoUseCase;
        this.scanner = new Scanner(System.in);
    }

    public void iniciarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- GESTIÓN DE VEHÍCULOS (CEA) ---");
            System.out.println("1. Registrar Vehículo");
            System.out.println("2. Buscar Vehículo por ID");
            System.out.println("3. Listar todos los Vehículos");
            System.out.println("4. Actualizar Vehículo");
            System.out.println("5. Eliminar Vehículo");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1 -> registrar();
                    case 2 -> buscarPorId();
                    case 3 -> listar();
                    case 4 -> actualizar();
                    case 5 -> eliminar();
                    case 0 -> System.out.println("Saliendo de la gestión de vehículos...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        }
    }

    private void registrar() {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        Vehiculo v = new Vehiculo(id, placa, marca, modelo);
        vehiculoUseCase.registrarVehiculo(v);
        System.out.println("Vehículo registrado exitosamente.");
    }

    private void buscarPorId() {
        System.out.print("Ingrese ID del vehículo: ");
        String id = scanner.nextLine();
        vehiculoUseCase.obtenerVehiculoPorId(id)
                .ifPresentOrElse(
                        v -> System.out.println("Encontrado: " + v.getPlaca() + " - " + v.getMarca() + " " + v.getModelo()),
                        () -> System.out.println("Vehículo no encontrado.")
                );
    }

    private void listar() {
        List<Vehiculo> lista = vehiculoUseCase.listarVehiculos();
        if (lista.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
        } else {
            System.out.println("\n--- LISTA DE VEHÍCULOS ---");
            lista.forEach(v -> System.out.println("ID: " + v.getId() + " | Placa: " + v.getPlaca() + " | Marca: " + v.getMarca() + " | Modelo: " + v.getModelo()));
        }
    }

    private void actualizar() {
        System.out.print("Ingrese ID del vehículo a actualizar: ");
        String id = scanner.nextLine();
        if (vehiculoUseCase.obtenerVehiculoPorId(id).isPresent()) {
            System.out.print("Nueva Placa: ");
            String placa = scanner.nextLine();
            System.out.print("Nueva Marca: ");
            String marca = scanner.nextLine();
            System.out.print("Nuevo Modelo: ");
            String modelo = scanner.nextLine();

            Vehiculo v = new Vehiculo(id, placa, marca, modelo);
            vehiculoUseCase.actualizarVehiculo(v);
            System.out.println("Vehículo actualizado exitosamente.");
        } else {
            System.out.println("Vehículo no encontrado.");
        }
    }

    private void eliminar() {
        System.out.print("Ingrese ID del vehículo a eliminar: ");
        String id = scanner.nextLine();
        if (vehiculoUseCase.eliminarVehiculo(id)) {
            System.out.println("Vehículo eliminado exitosamente.");
        } else {
            System.out.println("No se encontró el vehículo para eliminar.");
        }
    }

    public static void main(String[] args) {
        VehiculoOutputPort repository = new InMemoryVehiculoRepository();
        VehiculoUseCase useCase = new VehiculoUseCase(repository);
        VehiculoConsoleAdapter cli = new VehiculoConsoleAdapter(useCase);
        cli.iniciarMenu();
    }
}