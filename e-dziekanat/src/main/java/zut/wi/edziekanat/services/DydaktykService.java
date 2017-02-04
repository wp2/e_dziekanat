package zut.wi.edziekanat.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zut.wi.edziekanat.dao.DydaktykDao;
import zut.wi.edziekanat.entity.Dydaktyk;
import zut.wi.edziekanat.entity.PrzedmiotyDydaktyka;
import zut.wi.edziekanat.entity.StudentOceny;
import zut.wi.edziekanat.entity.Zatrudnienie;

@Service
public class DydaktykService 
{	
	@Autowired
	DydaktykDao dydaktykDao;
	
	public Dydaktyk getDydaktykById(Integer Id)
	{
		return this.dydaktykDao.getDydaktykById(Id);
	}
	
	public Zatrudnienie getDydaktykZatrudnienie(Integer Id)
	{
		return this.dydaktykDao.getDydaktykZatrudnienieInfo(Id);
	}
	
	public List<PrzedmiotyDydaktyka> getDydaktykPrzedmioty(Integer Id)
	{
		return this.dydaktykDao.getDydaktykPrzedmioty(Id);
	}
	
	public Map<PrzedmiotyDydaktyka,List<StudentOceny>> getKursyOceny(Integer IdPracownik)
	{
		Map<PrzedmiotyDydaktyka,List<StudentOceny>> ocenyKursów = new LinkedHashMap<PrzedmiotyDydaktyka,List<StudentOceny>>();
		List<PrzedmiotyDydaktyka> przedmioty = this.getDydaktykPrzedmioty(IdPracownik);
		for(PrzedmiotyDydaktyka pd : przedmioty)
		{
			List<StudentOceny> oceny = this.dydaktykDao.getOcenyzKursow(pd.getFormaProwadzącegoId());
			ocenyKursów.put(pd, oceny);
		}
		return ocenyKursów;
		//return this.dydaktykDao.getOcenyzKursow(Id);
	}

}
