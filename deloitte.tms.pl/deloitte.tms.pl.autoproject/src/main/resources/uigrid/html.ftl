<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign fields=pojo.getPojoFields()>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign declarationNameFirstLetterLower = pojo.importType(pojo.getDeclarationNameFirstLetterLower())>

<div ng-controller="${declarationName}Ctroller">
	<style>
	.grid {
	  height: 400px;
	}
	</style>
	<div class="form-body">
		<div class="form-group">  
		<#foreach field in fields>
		<#if field.getIsSampleField()&&field.getIsNotBaseEntiry()>
		<label class="col-md-2 control-label"><#if field.getComment()?exists>${field.getComment()}<#else>${field.getName()}</#if>:</label>
		<div class="col-md-2"> <input type="text" class="form-control" ng-model="${declarationNameFirstLetterLower}Query.${field.getName()}" placeholder=""> </div>
		</#if>                             
		</#foreach>                                                  
		</div>                                        
	</div>
	<div class="clearfix"></div>
	<div>
		<button type="button" class="btn btn-success" ng-click="query${declarationName}()">查询</button>
		<button type="button" class="btn btn-success" ng-click="add${declarationName}()">添加</button>
		<!--<button type="button" class="btn btn-success" ng-click="edit${declarationName}()">修改</button>-->
		<button type="button" class="btn btn-success" ng-click="removeSelect${declarationName}s()">删除选择</button>
		<button type="button" class="btn btn-success" ng-click="reset${declarationName}()">还原修改</button>
		<!--<button type="button" class="btn btn-success" ng-click="save${declarationName}()">保存修改</button>-->
	</div>
	<div ui-grid="${declarationNameFirstLetterLower}Grid" ui-grid-edit ui-grid-row-edit ui-grid-cellNav ui-grid-pagination ui-grid-selection class="grid"></div>
</div>