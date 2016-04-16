    <ClientEvent name="onReady">setTimeout(function(){&#xD;
	//view.help${declarationName}();&#xD;
},1000);&#xD;
</ClientEvent>
    <ClientEvent name="onCreate">
this.add${declarationName}=function()&#xD;
{&#xD;
	var masterData=this.id(&quot;dataSet${declarationName}&quot;).getData();&#xD;
	masterData.insert();&#xD;
	<#if pojo.getEditType().toString()=="grid">//<#else></#if>this.id(&quot;dialog${declarationName}&quot;).show();       &#xD;
}&#xD;
this.del${declarationName}=function()
{
	var masterData = this.id(&quot;dataSet${declarationName}&quot;).getData();&#xD;
	var updateActionSave${declarationName} = this.id(&quot;updateActionSave${declarationName}&quot;);&#xD;
	if(masterData.current){&#xD;
	<#assign child_str ="">		
	<#foreach child in many>
	<#assign childpropertyname = "${child.getDeclarationNameFirstLetterLower()}s"> 		
				var ${childpropertyname}=this.id(&quot;dataSet${declarationName}&quot;).getData(&quot;#.${childpropertyname}&quot;);
				<#if child_str==""><#assign child_str=childpropertyname+".current"><#else><#assign child_str=child_str+"||"+childpropertyname+".current"></#if>
	</#foreach>	
				<#if child_str=="">
				dorado.MessageBox.confirm(&quot;您真的想删除当前主表信息吗？&quot;,function(){
						masterData.remove();&#xD;
						updateActionSave${declarationName}.execute();&#xD;
					});&#xD;
				<#else>
				if(${child_str})
				{&#xD;
					dorado.MessageBox.show({
	                        title: &quot;操作错误&quot;,
	                        icon: dorado.MessageBox.ERROR_ICON,
	                        message: &quot;当前选中节点含有子节点项，请先删除子节点！&quot;,
	                        buttons: [&quot;ok&quot;]
	                    })
				}else{
					dorado.MessageBox.confirm(&quot;您真的想删除当前主表信息吗？&quot;,function(){
						masterData.remove();
						updateActionSave${declarationName}.execute();
					});	 
				}&#xD;
				</#if>			
			}
}
this.edit${declarationName}=function()&#xD;
{&#xD;
	var data = this.id(&quot;dataSet${declarationName}&quot;).getData();&#xD;
	if (data.current) {&#xD;
	    <#if pojo.getEditType().toString()=="grid">//<#else></#if>this.id(&quot;dialog${declarationName}&quot;).show();       &#xD;
	}else {&#xD;
	    dorado.MessageBox.alert(&quot;当前主表没有信息可供编辑！&quot;);&#xD;
	}&#xD;
}&#xD;
this.query${declarationName}=function()&#xD;
{&#xD;
	var ds = this.id(&quot;dataSet${declarationName}&quot;);&#xD;
	var form = this.id(&quot;formCondition${declarationName}&quot;);&#xD;
	ds.set(&quot;parameter&quot;,form.get(&quot;entity&quot;)).flushAsync();&#xD;
}&#xD;
this.save${declarationName}=function()&#xD;
{&#xD;
	var action = this.id(&quot;updateActionSave${declarationName}&quot;);&#xD;
	action.execute(function(){&#xD;
	});&#xD;
}&#xD;
this.saveSingle${declarationName}=function()
{
	var action = this.id(&quot;updateActionSave${declarationName}&quot;);
	action.execute(function(){
		this.id(&quot;dialog${declarationName}&quot;).hide();
	});
}
this.cancelSingle${declarationName}=function(){
	this.id(&quot;dataSet${declarationName}&quot;).getData().cancel();
	this.id(&quot;dialog${declarationName}&quot;).hide();
}&#xD;
this.help${declarationName}=function()&#xD;
{&#xD;
	this.id(&quot;intro${declarationName}&quot;).start();&#xD;
}&#xD;
this.inithelp${declarationName}=function()
{
	var intro = this.get('#intro${declarationName}');
	var items = [],steps = intro.get('steps');
	for (var i = 0,len = steps.length; i &lt; len; i++) {
	    var step = steps[i];
	    step.name &amp;&amp; items.push(step.name);
	};
	var dr=this.id(&quot;introDropDown${declarationName}&quot;);
	dr.set('items',items);
}
this.selecthelp${declarationName}=function(selectedValue)
{
	var intro=view.get('#intro${declarationName}');
	selectedValue &amp;&amp;intro.goTo(selectedValue);
}
<#include "viewfunction_dropdown.ftl"/>
</ClientEvent>