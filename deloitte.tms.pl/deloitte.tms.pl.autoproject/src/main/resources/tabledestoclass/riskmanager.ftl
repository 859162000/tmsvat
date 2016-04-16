<#assign tablename = table.getName()>
<#assign des = table.getDes()>
<#assign fields = table.getFields()>
<#assign key = table.getKey()>

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.ling2.core.commons.annotation.ModelProperty;
import com.ling2.core.commons.constant.TablePreDef;
import com.ling2.core.hibernate.identifier.Ling2IdGenerator;
import com.ling2.core.model.impl.LongRiskBaseEntity;

@Entity
@Table(name = TablePreDef.RISK+"${tablename}")
@ModelProperty(comment = "${des}",des="${des}")
public class ${tablename} extends LongRiskBaseEntity{
	@Id
	@GenericGenerator(name="${tablename}_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value="${tablename}")})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "${tablename}_GENERATOR")
	@Column(name = "${key}",  length = 16)
	Long id;
	
<#foreach field in fields>
	@Column(name = "${field.getColnum()}"<#if field.getLength()?exists>,  length = ${field.getLength()}<#else></#if>)
	@ModelProperty(comment="${field.getDes()}")
	${field.getType()} ${field.getField()};
	
</#foreach>
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
