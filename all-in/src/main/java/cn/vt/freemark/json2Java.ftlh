/**
* <#if classDesc?? && classDesc != "">${classDesc}<#else>${className}</#if>
*
* @author ${author} <#if time??>\n* @date ${time}</#if>
*/
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ${className} {
    <#list fields as field>
    @ApiModelProperty(value = "<#if (field.desc)??>${field.desc}<#else>${field.name}</#if>")
    private ${field.type} ${field.name};
    </#list>
}
