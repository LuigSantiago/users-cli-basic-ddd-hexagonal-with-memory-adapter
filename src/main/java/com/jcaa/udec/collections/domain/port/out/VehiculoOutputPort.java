package com.jcaa.udec.collections.domain.port.out;

import com.jcaa.udec.collections.domain.core.model.Vehiculo;
import java.util.List;
import java.util.Optional;

public interface VehiculoOutputPort {
    Vehiculo save(Vehiculo vehiculo);
    Optional<Vehiculo> findById(String id);
    List<Vehiculo> findAll();
    Vehiculo update(Vehiculo vehiculo);
    boolean deleteById(String id);
}