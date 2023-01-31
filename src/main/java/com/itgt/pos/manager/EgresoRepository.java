package com.itgt.pos.manager;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itgt.pos.model.Egreso;

public interface EgresoRepository extends JpaRepository<Egreso, Long>{

	List<Egreso> findByPersonaNodocumentoAndTipopagoAndPagopendienteGreaterThan(String nodocumento, int tipopago, float pagopendiente);
	
	List<Egreso> findByTipopagoAndPagopendienteGreaterThan(int tipopago, float pagopendiente);

	List<Egreso> findByEstadoAndTipoComprobanteAndFechaegresoAndSucursalId(int estado, int tipoComprobante, Date fechaegreso, Long id);

	List<Egreso> findByEstadoAndTipoComprobanteAndFechaegresoBetweenAndSucursalId(int estado, int tipoComprobante, Date start, Date end, Long id);
	
	
    @Query("select e from Egreso e inner join Sucursal s ON e.sucursal.id = s.id where DATE_FORMAT(e.fechaegreso,'%Y-%m-%d') = :fechaegreso\r\n"
    		+ "and e.estado = :estado\r\n"
    		+ "and e.tipopago = :tipopago\r\n" 
    		+ "and e.tipoComprobante = :tipocomprobante\r\n"
    		+ "and s.id = :idsucursal")
    List<Egreso> findAllWithFechaegreso(
    		@Param("fechaegreso") String fechaegreso,
    		@Param("estado") int estado,
    		@Param("tipopago") int tipopago,
    		@Param("tipocomprobante") int tipoComprobante,
    		@Param("idsucursal") Long id
    		);

    @Query("select e from Egreso e inner join Sucursal s ON e.sucursal.id = s.id where DATE_FORMAT(e.fechaegreso,'%Y-%m-%d') BETWEEN :start AND :end\r\n"
    		+ "and e.estado = :estado\r\n"
    		+ "and e.tipoComprobante = :tipocomprobante\r\n"
    		+ "and s.id = :idsucursal")
    List<Egreso> findAllWithFechaegresoBetween(
    		@Param("start") String start,
    		@Param("end") String end,
    		@Param("estado") int estado,
    		@Param("tipocomprobante") int tipoComprobante,
    		@Param("idsucursal") Long id
    		);
    
}