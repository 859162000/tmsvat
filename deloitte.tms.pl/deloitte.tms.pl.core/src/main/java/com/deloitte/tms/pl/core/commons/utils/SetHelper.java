/**
 * 
 */
package com.deloitte.tms.pl.core.commons.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xinzhi
 * 
 */
public class SetHelper {

	public static Set[] convert(Set ids) {
		/** 如果ids的size为0.则默认产生一个Long形变量 */
		if (ids == null || ids.size() < 1) {
			Set tmp = new HashSet();
			tmp.add(new Long(-1000000));
			return new Set[] { tmp };
		}

		Object[] array = ids.toArray();
		int length = array.length;
		if (length > 300) {
			int loopnum = 0;
			if (ids.size() % 300 == 0) {
				loopnum = length / 300;
			} else {
				loopnum = length / 300 + 1;
			}
			int begin = 0;
			int end = 300;

			Set[] results = new Set[loopnum];
			for (int i = 0; i < loopnum; i++) {
				Set result = new HashSet();
				if (end > length) {
					end = length;
				}
				for (int m = begin; m < end; m++) {
					result.add(array[m]);
				}
				begin = begin + 300;
				end = end + 300;
				results[i] = result;
			}
			return results;
		} else {
			return new Set[] { ids };
		}

	}

	public static Set[] convertToBiggest(Set ids) {
		/** 如果ids的size为0.则默认产生一个Long形变量 */
		if (ids == null || ids.size() < 1) {
			Set tmp = new HashSet();
			tmp.add(new Long(-1000000));
			return new Set[] { tmp };
		}

		Object[] array = ids.toArray();
		int length = array.length;
		if (length > 990) {
			int loopnum;
			if (ids.size() % 990 == 0) {
				loopnum = length / 990;
			} else {
				loopnum = length / 990 + 1;
			}
			int begin = 0;
			int end = 990;

			Set[] results = new Set[loopnum];
			for (int i = 0; i < loopnum; i++) {
				Set result = new HashSet();
				if (end > length) {
					end = length;
				}
				for (int m = begin; m < end; m++) {
					result.add(array[m]);
				}
				begin = begin + 990;
				end = end + 990;
				results[i] = result;
			}
			return results;
		} else {
			return new Set[] { ids };
		}
	}
}
