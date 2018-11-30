package beans;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Good implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8355600855723701250L;
	private String finderId, numberInfo, losterName, goodDesc, foundAddr, finderName, finderPhone, finderQQorWX,
			imagePath;

	private long gid;

	@Override
	public String toString() {
		return "Good [gid=" + gid + ", finderId=" + finderId + ", numberInfo=" + numberInfo + ", losterName="
				+ losterName + ", goodDesc=" + goodDesc + ", foundAddr=" + foundAddr + ", finderName=" + finderName
				+ ", finderPhone=" + finderPhone + ", finderQQorWX=" + finderQQorWX + ", imagePath=" + imagePath + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((finderId == null) ? 0 : finderId.hashCode());
		result = prime * result + ((finderName == null) ? 0 : finderName.hashCode());
		result = prime * result + ((finderPhone == null) ? 0 : finderPhone.hashCode());
		result = prime * result + ((finderQQorWX == null) ? 0 : finderQQorWX.hashCode());
		result = prime * result + ((foundAddr == null) ? 0 : foundAddr.hashCode());
		result = prime * result + ((goodDesc == null) ? 0 : goodDesc.hashCode());
		result = prime * result + ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + ((losterName == null) ? 0 : losterName.hashCode());
		result = prime * result + ((numberInfo == null) ? 0 : numberInfo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Good other = (Good) obj;
		if (finderId == null) {
			if (other.finderId != null)
				return false;
		} else if (!finderId.equals(other.finderId))
			return false;
		if (finderName == null) {
			if (other.finderName != null)
				return false;
		} else if (!finderName.equals(other.finderName))
			return false;
		if (finderPhone == null) {
			if (other.finderPhone != null)
				return false;
		} else if (!finderPhone.equals(other.finderPhone))
			return false;
		if (finderQQorWX == null) {
			if (other.finderQQorWX != null)
				return false;
		} else if (!finderQQorWX.equals(other.finderQQorWX))
			return false;
		if (foundAddr == null) {
			if (other.foundAddr != null)
				return false;
		} else if (!foundAddr.equals(other.foundAddr))
			return false;
		if (goodDesc == null) {
			if (other.goodDesc != null)
				return false;
		} else if (!goodDesc.equals(other.goodDesc))
			return false;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		if (losterName == null) {
			if (other.losterName != null)
				return false;
		} else if (!losterName.equals(other.losterName))
			return false;
		if (numberInfo == null) {
			if (other.numberInfo != null)
				return false;
		} else if (!numberInfo.equals(other.numberInfo))
			return false;
		return true;
	}

	public long getGid() {
		return gid;
	}

	public void setGid(long gid) {
		this.gid = gid;
	}

	public String getFinderId() {
		return finderId;
	}

	public void setFinderId(String finderId) {
		this.finderId = finderId;
	}

	public String getNumberInfo() {
		return numberInfo;
	}

	public void setNumberInfo(String numberInfo) {
		this.numberInfo = numberInfo;
	}

	public String getLosterName() {
		return losterName;
	}

	public void setLosterName(String losterName) {
		this.losterName = losterName;
	}

	public String getGoodDesc() {
		return goodDesc;
	}

	public void setGoodDesc(String goodDesc) {
		this.goodDesc = goodDesc;
	}

	public String getFoundAddr() {
		return foundAddr;
	}

	public void setFoundAddr(String foundAddr) {
		this.foundAddr = foundAddr;
	}

	public String getFinderName() {
		return finderName;
	}

	public void setFinderName(String finderName) {
		this.finderName = finderName;
	}

	public String getFinderPhone() {
		return finderPhone;
	}

	public void setFinderPhone(String finderPhone) {
		this.finderPhone = finderPhone;
	}

	public String getFinderQQorWX() {
		return finderQQorWX;
	}

	public void setFinderQQorWX(String finderQQorWX) {
		this.finderQQorWX = finderQQorWX;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getSearchString() {
		return numberInfo + losterName + goodDesc + foundAddr;
	}
}
