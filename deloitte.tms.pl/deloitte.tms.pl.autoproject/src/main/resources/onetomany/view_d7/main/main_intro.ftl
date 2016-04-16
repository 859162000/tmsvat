    <ListDropDown id="introDropDown${declarationName}">
      <Property name="autoOpen">true</Property>
      <ClientEvent name="onClose">this.selecthelp${declarationName}(arg.selectedValue)</ClientEvent>
      <ClientEvent name="onReady">this.inithelp${declarationName}()</ClientEvent>
    </ListDropDown>
    <Intro id="intro${declarationName}">
      <Property name="nextLabel">下一步</Property>
      <Property name="prevLabel">上一步</Property>
      <Property name="skipLabel">跳出</Property>
      <Property name="doneLabel">完成</Property>
      <Property name="showStepNumbers">true</Property>
      <Step>
        <Property name="element">toolBar${declarationName}</Property>
        <Property name="intro">&lt;h1 class=&quot;intro-title&quot;>品牌管理&lt;/h1>&#xD;
&lt;div class=&quot;intro-content&quot;>&#xD;
    &lt;h3>简介&lt;/h3>&#xD;
&lt;pre class=&quot;content&quot;>&#xD;
查询:按上面的条件查询数据,结果在下面的表格显示&#xD;
添加:新增一条数据&#xD;
<#if pojo.getEditType().toString()=="from">修改:修改当前选中的数据(当前选中数据为淡绿色)&#xD;<#else></#if>
删除:删除当前选中的数据(当前选中数据为淡绿色)&#xD;
保存:保存当前页面所有对数据的修改&#xD;
&lt;/pre>&#xD;
&#xD;
    &lt;h3>操作快捷键介绍&lt;/h3>&#xD;
    &lt;ul>&#xD;
        &lt;li>&lt;label> Esc&lt;/label>&lt;label class=&quot;description&quot;>--退出帮助文档。&lt;/label>&lt;/li>&#xD;
        &lt;li>&lt;label> &amp;rarr;&lt;/label>&lt;label class=&quot;description&quot;>--下一步。&lt;/label>&lt;/li>&#xD;
        &lt;li>&lt;label> &amp;larr;&lt;/label>&lt;label class=&quot;description&quot;>--上一步。&lt;/label>&lt;/li>&#xD;
    &lt;/ul>&#xD;
&lt;/div>&#xD;
</Property>
        <Property name="name">功能介绍</Property>
      </Step>
      <Step>
        <Property name="element">formCondition${declarationName}</Property>
        <Property name="intro">&lt;h1 class=&quot;intro-title&quot; >查询条件区域&lt;/h1>&#xD;
&lt;div class=&quot;intro-content&quot;>&#xD;
&lt;div>可在此区域输入过滤条件。&lt;/div>&#xD;
    &lt;h3>属性介绍&lt;/h3>&#xD;
    &lt;ul>&#xD;
        &lt;li>&lt;label>代码:&lt;/label>&lt;label class=&quot;description&quot;>代码&lt;/label>&lt;/li>&#xD;
    &lt;/ul>&#xD;
&lt;div></Property>
        <Property name="name">查询条件</Property>
      </Step>
      <Step>
        <Property name="element">searchBtn${declarationName}</Property>
        <Property name="intro">&lt;h1 class=&quot;intro-title&quot; >查询按钮&lt;/h1>&#xD;
&lt;div class=&quot;intro-content&quot;>&#xD;
&lt;div>单击此按钮按照填入的查询条件发起一次查询请求&lt;/div>&#xD;
    &lt;h3>相关介绍&lt;/h3>&#xD;
      &lt;div class=&quot;description&quot;>单击此按钮按照填入的查询条件发起一次查询请求&lt;/div>&#xD;
&lt;div>&#xD;
&#xD;
</Property>
        <Property name="name">查询按钮</Property>
      </Step>
      <Step>
        <Property name="intro">&lt;h1 class=&quot;intro-title&quot; >查询结果列表&lt;/h1> &#xD;
&lt;div class=&quot;intro-content&quot;>&#xD;
&lt;div>查询结果&lt;/div>&#xD;
&lt;div> &lt;/div>&#xD;
&lt;div>&#xD;
</Property>
        <Property name="element">dataGrid${declarationName}</Property>
        <Property name="name">查询结果</Property>
      </Step>
      <#if pojo.getEditType().toString()=="form">
      <Step>
        <Property name="name">点击修改按钮</Property>
        <Property name="element">modifyBtn${declarationName}</Property>
        <Property name="intro">点击修改按钮修改你当前选中的数据</Property>
      </Step>
      <Step>
        <Property name="name">修改内容</Property>
        <Property name="element">autoFormModify${declarationName}</Property>
        <Property name="intro">按需要修改数据</Property>
      </Step>
      <Step>
        <Property name="name">保存修改</Property>
        <Property name="element">saveSingleBtn${declarationName}</Property>
        <Property name="intro">点击保存按钮保存修改的数据</Property>
      </Step>
      <Step>
        <Property name="name">取消修改</Property>
        <Property name="element">cancelBtn${declarationName}</Property>
        <Property name="intro">也可以点击取消按钮取消本次修改,并关闭弹出窗口</Property>
      </Step>
      <#elseif pojo.getEditType().toString()=="grid">
      <Step>
        <Property name="intro">&lt;h1 class=&quot;intro-title&quot; >修改结果列表&lt;/h1> &#xD;
&lt;div class=&quot;intro-content&quot;>&#xD;
&lt;div>单击列头可进行排序，单击行可切换当前选中数据&lt;/div>&#xD;
&lt;div>修改的时候回车键切换下一个编辑框&lt;/div>&#xD;
&lt;div>&#xD;
</Property>
        <Property name="element">dataGrid${declarationName}</Property>
        <Property name="name">数据编辑</Property>
      </Step>
      <#else></#if>
      <Step>
        <Property name="element">saveBtn${declarationName}</Property>
        <Property name="intro">&lt;h3>保存按钮&lt;/h3>&#xD;
单击可将表格中修改过的所有数据保存到服务端</Property>
        <Property name="name">保存按钮</Property>
      </Step>
      <Step>
        <Property name="name">功能定位</Property>
        <Property name="intro">&lt;h1 class=&quot;intro-title&quot; >功能定位&lt;/h1>&#xD;
&lt;div class=&quot;intro-content&quot;>&#xD;
    &lt;div>请选择下拉框内的所需帮助。&lt;/div>&#xD;
&lt;div>&#xD;
</Property>
        <Property name="element">textEditorHelp${declarationName}</Property>
      </Step>
      <Step>
        <Property name="element">helpBtn${declarationName}</Property>
        <Property name="intro">通过此按钮可重新开始的哦！&#xD;
&lt;div class=&quot;intro-success&quot;>&#xD;
&lt;h1>结束！&lt;/h1>&#xD;
&lt;div>&#xD;
	感谢您的使用&#xD;
&lt;/div>&#xD;
&lt;/div>&#xD;
&#xD;
</Property>
        <Property name="name">帮助</Property>
      </Step>
    </Intro>