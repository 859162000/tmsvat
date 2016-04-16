package com.deloitte.tms.base.taxsetting.model;

import com.deloitte.tms.pl.core.model.impl.BaseEntity;

public class TaxSettingParam extends BaseEntity {

	private String Id;

	private String p_hs_trans_date;
	private String p_proc_hs_inf_flag;
	private String p_proc_ss_inf_flag;
	private String p_archive_base_date;
	private String p_biz_org_code;
	private String p_org_id;
	private String p_created_by;
	private String p_creation_date;
	private String p_last_updated_by;
	private String p_last_update_date;
	private String p_record_version;
	private String p_deleted_flag;
	private String p_deletion_date;

	public String getP_org_id() {
		return p_org_id;
	}

	public void setP_org_id(String p_org_id) {
		this.p_org_id = p_org_id;
	}

	public String getP_hs_trans_date() {
		return p_hs_trans_date;
	}

	public void setP_hs_trans_date(String p_hs_trans_date) {
		this.p_hs_trans_date = p_hs_trans_date;
	}

	public String getP_proc_hs_inf_flag() {
		return p_proc_hs_inf_flag;
	}

	public void setP_proc_hs_inf_flag(String p_proc_hs_inf_flag) {
		this.p_proc_hs_inf_flag = p_proc_hs_inf_flag;
	}

	public String getP_proc_ss_inf_flag() {
		return p_proc_ss_inf_flag;
	}

	public void setP_proc_ss_inf_flag(String p_proc_ss_inf_flag) {
		this.p_proc_ss_inf_flag = p_proc_ss_inf_flag;
	}

	public String getP_archive_base_date() {
		return p_archive_base_date;
	}

	public void setP_archive_base_date(String p_archive_base_date) {
		this.p_archive_base_date = p_archive_base_date;
	}

	public String getP_biz_org_code() {
		return p_biz_org_code;
	}

	public void setP_biz_org_code(String p_biz_org_code) {
		this.p_biz_org_code = p_biz_org_code;
	}

	public String getP_created_by() {
		return p_created_by;
	}

	public void setP_created_by(String p_created_by) {
		this.p_created_by = p_created_by;
	}

	public String getP_creation_date() {
		return p_creation_date;
	}

	public void setP_creation_date(String p_creation_date) {
		this.p_creation_date = p_creation_date;
	}

	public String getP_last_updated_by() {
		return p_last_updated_by;
	}

	public void setP_last_updated_by(String p_last_updated_by) {
		this.p_last_updated_by = p_last_updated_by;
	}

	public String getP_last_update_date() {
		return p_last_update_date;
	}

	public void setP_last_update_date(String p_last_update_date) {
		this.p_last_update_date = p_last_update_date;
	}

	public String getP_record_version() {
		return p_record_version;
	}

	public void setP_record_version(String p_record_version) {
		this.p_record_version = p_record_version;
	}

	public String getP_deleted_flag() {
		return p_deleted_flag;
	}

	public void setP_deleted_flag(String p_deleted_flag) {
		this.p_deleted_flag = p_deleted_flag;
	}

	public String getP_deletion_date() {
		return p_deletion_date;
	}

	public void setP_deletion_date(String p_deletion_date) {
		this.p_deletion_date = p_deletion_date;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}
}
