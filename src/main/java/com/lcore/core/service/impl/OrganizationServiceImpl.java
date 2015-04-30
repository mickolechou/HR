package com.lcore.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lcore.core.entity.Organization;
import com.lcore.core.entity.Root;
import com.lcore.core.service.OrganizationService;

@Service("organizationService")
@Transactional
public class OrganizationServiceImpl extends BaseServiceImpl implements OrganizationService {

	@Override
	public List<Root> getOuList(int offset,int limit,String sort,String order,String key) {
		//构造查询条件:所有字段模糊匹配
		String condition = " (obj.createTime like '%"+key+"%' ";
		condition+=" or obj.updateTime like '%"+key+"%' ";
		condition+=" or obj.startTime like '%"+key+"%' ";
		condition+=" or obj.endTime like '%"+key+"%' ";
		condition+=" or obj.ouName like '%"+key+"%' ";
		condition+=" or obj.updater like '%"+key+"%' ";
		condition+=" or obj.creater like '%"+key+"%' )";
		if(null!=sort&&!"".equals(sort))
	      	condition+=" order by "+" obj."+sort+"" + "  "+order;
		return this.getPagedObjListWithCondition(Organization.class.getSimpleName(), condition, offset, limit);
	}

	@Override
	public void addOu(Organization org) throws Exception {
		org.setStartTime(new Date());
		this.saveObj(org);
	}

}