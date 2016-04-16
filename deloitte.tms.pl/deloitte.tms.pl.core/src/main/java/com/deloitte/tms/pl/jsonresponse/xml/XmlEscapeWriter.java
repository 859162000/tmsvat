package com.deloitte.tms.pl.jsonresponse.xml;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.lang.StringUtils;

public class XmlEscapeWriter extends Writer {
	private Writer writer;
	private boolean escapeEnabled;

	public XmlEscapeWriter(Writer writer) {
		this.writer = writer;
	}

	public boolean isEscapeEnabled() {
		return escapeEnabled;
	}

	public void setEscapeEnabled(boolean escapeEnabled) {
		this.escapeEnabled = escapeEnabled;
	}

	public String escapeCDataContent(String str) {
		if (str == null) {
			return null;
		}
		return StringUtils.replace(str, "]]>", "]]]]><![CDATA[>");
	}

	@Override
	public void close() throws IOException {
		writer.close();
	}

	@Override
	public void flush() throws IOException {
		writer.flush();
	}

	@Override
	public Writer append(char c) throws IOException {
		return writer.append(c);
	}

	@Override
	public Writer append(CharSequence csq, int start, int end)
			throws IOException {
		if (escapeEnabled) {
			if (csq == null) {
				return writer.append(null, start, end);
			} else {
				return super.append(escapeCDataContent(csq.toString()), start,
						end);
			}
		} else {
			return writer.append(csq, start, end);
		}
	}

	@Override
	public Writer append(CharSequence csq) throws IOException {
		if (escapeEnabled) {
			if (csq == null) {
				return writer.append(null);
			} else {
				return super.append(escapeCDataContent(csq.toString()));
			}
		} else {
			return writer.append(csq);
		}
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		writer.write(cbuf, off, len);
	}

	@Override
	public void write(char[] cbuf) throws IOException {
		writer.write(cbuf);
	}

	@Override
	public void write(String str, int off, int len) throws IOException {
		if (escapeEnabled) {
			writer.write(escapeCDataContent(str), off, len);
		} else {
			writer.write(str, off, len);
		}
	}

	@Override
	public void write(String str) throws IOException {
		if (escapeEnabled) {
			writer.write(escapeCDataContent(str));
		} else {
			writer.write(str);
		}
	}

	@Override
	public void write(int c) throws IOException {
		writer.write(c);
	}
}