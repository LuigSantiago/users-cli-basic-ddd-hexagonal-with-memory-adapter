package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.model.Vehiculo;
import com.jcaa.udec.collections.domain.port.out.VehiculoOutputPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryVehiculoRepository implements VehiculoOutputPort {
    private final List<Vehiculo> vehiculos = new ArrayList<>();

    @Override
    public Vehiculo save(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
        return vehiculo;
    }

    @Override
    public Optional<Vehiculo> findById(String id) {
        return vehiculos.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Vehiculo> findAll() {
        return new ArrayList<>(vehiculos);
    }

    @Override
    public Vehiculo update(Vehiculo vehiculo) {
        deleteById(vehiculo.getId());
        vehiculos.add(vehiculo);
        return vehiculo;
    }

    @Override
    public boolean deleteById(String id) {
        return vehiculos.removeIf(v -> v.getId().equals(id));
    }
}