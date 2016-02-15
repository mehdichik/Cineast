//package com.MovieVault.mb;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//
//import javax.ejb.EJB;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//import javax.faces.context.FacesContext;
//
//import MovieVault.Persistence.Admin;
//
//import MovieVault.Persistence.Message;
//import MovieVault.Persistence.Personne;
//import MovieVault.Persistence.Admin;
//import MovieVault.Services.MessageServiceRemote;
//import MovieVault.Services.AdminServicesLocal;;
//
//
//@ManagedBean
//@ViewScoped
//public class MsgBean  implements Serializable{
//	
//	private static final long serialVersionUID = 1L;
//
//	private Admin client = new Admin();
//	private List<Admin> clients ;
//	private boolean listeOfClientsVisibility = false;
//	private Admin selecteClient ;
//	private boolean aa = true;
//	private Message message = new Message();
//	private String txt;
//	Admin user1= new Admin();
//	Date d =new Date();
//	
//	
//	@EJB
//	private AdminServicesLocal clientservicelocal ;
//	
//	@EJB
//	private MessageServiceRemote messageservicelocal ;
//	
//	public MsgBean() {
//		super();
//	}
//	
//
//	public Admin getClient() {
//		return client;
//	}
//
//	public void setClient(Admin client) {
//		this.client = client;
//	}
//
//	public List<Admin> getClients() {
//		clients= clientservicelocal.findAllAdmis();
//		return clients;
//	}
//
//	public void setClients(List<Admin> clients) {
//		this.clients = clients;
//	}
//	
//	
//	
//	public String enableVisibility() {
//		setListeOfClientsVisibility(true);
//		return "";
//	}
//	
//	public String disableVisibility() {
//		setListeOfClientsVisibility(false);
//		return "";
//	}
//	
//
//	public boolean isListeOfClientsVisibility() {
//		return listeOfClientsVisibility;
//	}
//
//	public void setListeOfClientsVisibility(boolean listeOfClientsVisibility) {
//		this.listeOfClientsVisibility = listeOfClientsVisibility;
//	}
//
//	
//
//
//	public Admin getSelecteClient() {
//		return selecteClient;
//	}
//
//	public void setSelecteClient(Admin selecteClient) {
//		this.selecteClient = selecteClient;
//	}
//	
//	public List<Message> aficheMessage(int idd)
//	{
//		if (aa) {
//			idd = 1;
//			aa = false;
//		}
//		AuthenticationBean me = (AuthenticationBean)
//				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authBean");
//		List<Message> mm = messageservicelocal.getMessageByClents(idd,me.getAdmin().getId());
//		
//		return mm;
//	}
//	
//	public List<Personne> friendnotme() { 
//		AuthenticationBean me = (AuthenticationBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authBean");
//		List<Personne> cc= clientservicelocal.findAdminNotMe(me.getAdmin().getId());
//		return cc; }
//	
//	
//
//	
//	public void doNew(){
//		message= new Message();
//		AuthenticationBean me = (AuthenticationBean)
//				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authBean");
//		message.setDate(d);
//		message.setpReceive(selecteClient);
//		message.setpSend(me.getAdmin());
//		message.setText(txt);
//		messageservicelocal.addMessage(message);
//		
//	}
//	
//	
//	
//	public List<Admin> friend()
//	{
//		List<Admin> cc = clientservicelocal.findAllAdmis();
//		AuthenticationBean me = (AuthenticationBean)
//				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("authBean");	
//		cc.remove(me.getAdmin());
//		return cc;
//	}
//
//	public boolean isAa() {
//		return aa;
//	}
//
//
//	public void setAa(boolean aa) {
//		this.aa = aa;
//	}
//
//
//	public Message getMessage() {
//		return message;
//	}
//
//
//	public void setMessage(Message message) {
//		this.message = message;
//	}
//
//
//	public String getTxt() {
//		return txt;
//	}
//
//
//	public void setTxt(String txt) {
//		this.txt = txt;
//	}
//
//}
