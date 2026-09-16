package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.domain.core.model.Vehiculo;
import com.jcaa.udec.collections.domain.port.out.VehiculoOutputPort;

import java.util.List;
import java.util.Optional;

public class VehiculoUseCase {

    private final VehiculoOutputPort vehiculoOutputPort;

    public VehiculoUseCase(VehiculoOutputPort vehiculoOutputPort) {
        this.vehiculoOutputPort = vehiculoOutputPort;
    }

    public Vehiculo registrarVehiculo(Vehiculo vehiculo) {
        return vehiculoOutputPort.save(vehiculo);
    }

    public Optional<Vehiculo> obtenerVehiculoPorId(String id) {
        return vehiculoOutputPort.findById(id);
    }

    public List<Vehiculo> listarVehiculos() {
        return vehiculoOutputPort.findAll();
    }

    public Vehiculo actualizarVehiculo(Vehiculo vehiculo) {
        return vehiculoOutputPort.update(vehiculo);
    }

    public boolean eliminarVehiculo(String id) {
        return vehiculoOutputPort.deleteById(id);
    }
}