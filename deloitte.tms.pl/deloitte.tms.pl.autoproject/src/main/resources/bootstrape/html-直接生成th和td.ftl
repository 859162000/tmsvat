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
								<div class="portlet box purple">
                                <div class="portlet-title">
                                    <div class="tools pull-left">
                                       	<button type="button" class="btn btn-success" ng-click="query${declarationName}()">查询</button>
										<button type="button" class="btn btn-success" data-toggle="modal" data-target="#add${declarationName}Detail" ng-click="add${declarationName}()">添加</button>
										<!--<button type="button" class="btn btn-success" data-toggle="modal" data-target="#edit${declarationName}Detail" ng-click="edit${declarationName}()">修改</button>-->
										<button type="button" class="btn btn-success" ng-click="removeSelect${declarationName}s()">删除选择</button>
										<button type="button" class="btn btn-success" ng-click="reset${declarationName}()">还原修改</button>
										<!--<button type="button" class="btn btn-success" ng-click="save${declarationName}()">保存修改</button>-->
										<!--<button type="button" class="btn btn-success" ng-click="save${declarationName}()">保存修改</button>-->
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div class="table-scrollable" style="height:300px">
                                        <table class="table table-striped table-bordered table-hover">
                                            <thead>
                                                <tr>
                                                    <th scope="col" style="width:450px !important"> checkbox </th>
													<#foreach field in fields>
													<#if field.getIsSampleField()&&field.getIsNotBaseEntiry()>
													<th scope="col">													
														<div class="cell" style="overflow: hidden; width: 120px;text-align: center;">
															<label class="caption"><#if field.getComment()?exists>${field.getComment()}<#else>${field.getName()}</#if></label>
														</div>
													</th>
													</#if>                             
													</#foreach>    
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr ng-repeat="${declarationNameFirstLetterLower} in ${declarationNameFirstLetterLower}Items" align="center">
                                                    <td> checkbox </td>
                                                    <#foreach field in fields>
													<#if field.getIsSampleField()&&field.getIsNotBaseEntiry()>
													<td> 
														<div class="cell" title="" style="overflow-x: hidden; overflow-y: visible" ng-bind="${declarationNameFirstLetterLower}.${field.getName()}"></div>
													</td>
													</#if>                             
													</#foreach> 
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
									<div  class="modal fade" id="add${declarationName}Detail" tabindex="-1" role="dialog" aria-hidden="true">
                                        <div class="modal-dialog modal-full">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                    <h4 class="modal-title">新增</h4>
                                                </div>
                                                <div class="modal-body"> 
											        <div class="form-body">
														<div class="form-group">  
														<#foreach field in fields>
														<#if field.getIsSampleField()&&field.getIsNotBaseEntiry()>
															<label class="col-md-2 control-label"><#if field.getComment()?exists>${field.getComment()}<#else>${field.getName()}</#if>:</label>
															<div class="col-md-2"> <input type="text" class="form-control" ng-model="current${declarationName}.${field.getName()}" placeholder=""> </div>
														</#if>                             
														</#foreach>                                                  
														</div>                                        
													</div>
												 </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn dark btn-outline" data-dismiss="modal">Close</button>
                                                    <button type="button" class="btn green"data-dismiss="modal" ng-click="test()">Save changes</button>
                                                </div>
                                            </div>
                                            <!-- /.modal-content -->
                                        </div>
                                        <!-- /.modal-dialog -->
                                    </div>
                                    <div  class="modal fade" id="edit${declarationName}Detail" tabindex="-1" role="dialog" aria-hidden="true">
                                        <div class="modal-dialog modal-full">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                    <h4 class="modal-title">编辑</h4>
                                                </div>
                                                <div class="modal-body"> 
											        <div class="form-body">
														<div class="form-group">  
														<#foreach field in fields>
														<#if field.getIsSampleField()&&field.getIsNotBaseEntiry()>
															<label class="col-md-2 control-label"><#if field.getComment()?exists>${field.getComment()}<#else>${field.getName()}</#if>:</label>
															<div class="col-md-2"> <input type="text" class="form-control" ng-model="current${declarationName}.${field.getName()}" placeholder=""> </div>
														</#if>                             
														</#foreach>                                                  
														</div>                                        
													</div>
												 </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn dark btn-outline" data-dismiss="modal">Close</button>
                                                    <button type="button" class="btn green"data-dismiss="modal" ng-click="test()">Save changes</button>
                                                </div>
                                            </div>
                                            <!-- /.modal-content -->
                                        </div>
                                        <!-- /.modal-dialog -->
                                    </div>