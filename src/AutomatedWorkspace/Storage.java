package AutomatedWorkspace;
/* Storage class with all useful methods, getters and setters, constructors 
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Storage {
	
	private String name;
	protected static List<Group> groups;
	protected static List<Goods> goods;
	
	public Storage() {
		this.name = "";
		Storage.groups = new ArrayList<>();
		Storage.goods = new ArrayList<>();
	}
	
	public Storage(String name) {
		this.name = name;
		Storage.groups = new ArrayList<>();
		Storage.goods = new ArrayList<>();
		groups.add(new Group("Сухі"));
		groups.get(0).setDescription("Не потребують збереження в холодильнику");
		groups.get(0).addGood(new Goods("Ложки"));
		groups.get(0).addGood(new Goods("Стаканчики"));
		groups.get(0).addGood(new Goods("Посипка КітКат"));
		groups.get(0).addGood(new Goods("Соус Кисло-солодкий"));
		groups.add(new Group("Кулерні"));
		groups.get(1).setDescription("Зберігання при температурі у 0-5 градусів");
		groups.get(1).addGood(new Goods("Помідори"));
		groups.get(1).addGood(new Goods("Огірки"));
		groups.get(1).addGood(new Goods("Соус Журавлиний"));
		groups.get(1).addGood(new Goods("Сироп Ванільний"));
		groups.add(new Group("Фрізерні"));
		groups.get(2).setDescription("Зберігання при температурі у -5-0 градусів");
		groups.get(2).addGood(new Goods("Булки для бургерів"));
		groups.get(2).addGood(new Goods("Суміш для морозива"));
		groups.get(2).addGood(new Goods("Нарізана картопля Фрі"));
		
		
		try {
			try (PrintWriter printOut = new PrintWriter("_Storage.txt")) {
				printOut.println(Storage.toStringGroups());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			try (PrintWriter printOut = new PrintWriter(groups.get(0).getName() + ".txt")) {
				printOut.println(groups.get(0).toString());
			}
			try (PrintWriter printOut = new PrintWriter(groups.get(1).getName() + ".txt")) {
				printOut.println(groups.get(1).toString());
			}
			try (PrintWriter printOut = new PrintWriter(groups.get(2).getName() + ".txt")) {
				printOut.println(groups.get(2).toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//		System.out.println(Storage.toStringGoods());
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static List<Group> getGroupsOfGoods() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		Storage.groups = groups;
	}

	public static int getNumberOfGoods() {
		int num = 0;
		for (Group gr : Storage.getGroupsOfGoods())
			for (@SuppressWarnings("unused") Goods g : gr.getGoods())
				num++;
		return num;
	}

	public void setGoods(List<Goods> goods) {
		Storage.goods = goods;
	}
	
	
	public void addGroup(Group group) {
		groups.add(group);
	}
	
	public void addGroup(String groupName) {
		groups.add(new Group(groupName));
	}
	
	public void deleteGroup(Group group) {
		groups.remove(group);
	}
	
	public void deleteGroup(String groupName) {
		groups.remove(new Group(groupName));
	}
	
	public static double getCostOfStorage() {
		double num = 0;
		for (Group gr : Storage.getGroupsOfGoods())
			num += gr.getCostOfGroup();
		return num;
	}
	
	public static boolean checkGroupName(String name) {
		for (Group gr: Storage.getGroupsOfGoods()) {
			if (gr.getName().equals(name))
				return false;
		}
		return true;
	}
	
	public static boolean checkGoodsName(String name) {
		for (Group gr : Storage.getGroupsOfGoods()) {
			for (Goods g : gr.goods)
				if (g.getName().equals(name))
					return false;
		}
		return true;
	}
	
	
	public static String toStringGroups() {
		String res = "";
		int num = 1;
		for (Group gr : Storage.getGroupsOfGoods()) {
			res += num + ") " + gr.getName() + "\n";
			res += "Опис: " + gr.getDescription() + "\n";
			num++;
		}
		return res;
	}
}
