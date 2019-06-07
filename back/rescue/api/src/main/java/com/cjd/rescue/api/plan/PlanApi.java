package com.cjd.rescue.api.plan;

import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.product.AddApollosParams;
import com.cjd.rescue.entity.product.AddPlanParams;
import com.cjd.rescue.entity.product.Apollo;
import com.cjd.rescue.entity.product.Plan;

public interface PlanApi {


    ReturnT list();

    ReturnT add(AddPlanParams addProjectParams);

    ReturnT delete(Plan plan);

    ReturnT get(Plan plan);

    ReturnT getOnLineInfo(Plan plan);

    ReturnT getModuleApollo(Apollo apollo);

    ReturnT setModuleApollo(AddApollosParams addApollosParams);
}
