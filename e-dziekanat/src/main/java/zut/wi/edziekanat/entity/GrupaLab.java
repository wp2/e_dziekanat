package zut.wi.edziekanat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

@Entity
public class GrupaLab 
{
	@Id
	@Column(name="Id" , updatable = false) // PK nigdy nie powinien być edytowalny	
	String id;
	
	@Column(name="Licznosc")
	int licznosc;
	
	@Column(name="IdGrupaCw")
	String idGrupaCw;

}
