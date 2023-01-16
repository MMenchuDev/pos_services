package com.itgt.pos.manager;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itgt.pos.model.Egreso;

public interface EgresoRepository extends JpaRepository<Egreso, Long>{

	List<Egreso> findByPersonaNodocumentoAndTipopagoAndPagopendienteGreaterThan(String nodocumento, int tipopago, float pagopendiente);
	
	List<Egreso> findByTipopagoAndPagopendienteGreaterThan(int tipopago, float pagopendiente);

	List<Egreso> findByEstadoAndTipoComprobanteAndFechaegresoAndUsuarioSucursalId(int estado, int tipoComprobante, Date fechaegreso, Long id);

	List<Egreso> findByEstadoAndTipoComprobanteAndFechaegresoBetweenAndSucursalId(int estado, int tipoComprobante, Date start, Date end, Long id);
	
}