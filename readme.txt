
# 📍 Smart Campus Navigation & Faculty Geotag Notification System (with Role-Based Dashboards)

---

## 🔹 **Role-Based Access**

* **Admin:** Manages system (users, buildings, events).
* **Faculty:** Geotags events, notifies classes.
* **Students:** Receive notifications, view navigation.

Authentication (MVP):

* Simple **login screen** with role selection (username/password stored in DB).
* Expansion → Secure login with hashed passwords, JWT, or LDAP integration.

---

## 🔹 **Dashboards**

### 1️⃣ **Admin Dashboard**

**Purpose:** Manage campus map & users.

**Features:**

* **User Management:**

  * Add/edit/remove students & faculty.
  * Assign courses/classes to students.
  * Set roles (Faculty/Student).

* **Campus Management:**

  * Add/edit/remove buildings on map.
  * Define paths (edges) between buildings for navigation.

* **Event Oversight:**

  * View all geotagged events created by faculty.
  * Delete inappropriate/expired events.
  * Monitor system activity logs.

* **Analytics (Expansion):**

  * Track most-used routes.
  * Attendance at geotagged locations.

---

### 2️⃣ **Faculty Dashboard**

**Purpose:** Create/manage events for students.

**Features:**

* **Geotag Event Creation:**

  * Select building or click on map.
  * Enter event details (name, type, time, message).
  * Push notification to relevant class.

* **Event Management:**

  * Edit/update event details.
  * Cancel/remove an event.
  * See which students viewed/joined the event (optional expansion).

* **Multi-Event Control:**

  * Create multiple events in one go.
  * Filter events by type (Lecture, Lab, Club, Emergency).

---

### 3️⃣ **Student Dashboard**

**Purpose:** Receive guidance & notifications.

**Features:**

* **Event Feed:**

  * List of upcoming/current events for their class/course.
  * Events sorted by time or type.

* **Interactive Map:**

  * See campus layout with event markers.
  * Click an event → shortest path drawn.
  * Animated navigation with moving dot.

* **Notification Center:**

  * Push alerts for new events or emergencies.
  * Interactive → click to open map + navigate.

* **Filters:**

  * Show only relevant event types (Class, Lab, Club).

---

## 🔹 **Architecture (Modules)**

1. **Authentication & Role Management**

   * Handles login + dashboard redirection.

2. **Admin Module**

   * User & campus management.

3. **Faculty Module**

   * Geotagging & event creation.

4. **Student Module**

   * Event feed, notifications, navigation.

5. **Navigation Engine**

   * Pathfinding (Dijkstra/A\*) + animated navigation.

6. **Notification System**

   * In-app alerts (MVP), expandable to Firebase/SMS.

7. **Database Layer**

   * Tables:

     * `Users (id, name, role, courseId)`
     * `Buildings (id, name, x, y)`
     * `Paths (id, fromBuilding, toBuilding, distance)`
     * `Events (id, createdBy, courseId, type, time, message, x, y)`

---

## 🔹 **Use Case Scenarios with Dashboards**

1. **Admin Onboarding New Batch**

   * Adds students + assigns them to courses.
   * Updates campus map with new building (e.g., “AI Lab”).

2. **Faculty Creating Emergency Drill**

   * Geotags “Assembly Point near C Block” → notification sent.
   * Students click → guided navigation to safety spot.

3. **Student Finding Relocated Class**

   * Receives push: “CS101 shifted to Seminar Hall at 11:30 AM.”
   * Opens Student Dashboard → sees event marker.
   * Animated navigation shows fastest route.

---

## 🔹 **Uniqueness (With Dashboards)**

* **Full role-based control** (Admin, Faculty, Student).
* **Centralized management** of events & navigation.
* **Interactive + Real-time** → unlike static notice boards or WhatsApp announcements.
* **Expandable** → can evolve into a complete **Smart Campus Management System**.

---

✅ With Admin + Faculty + Student dashboards, your project goes from “navigation app” → **a real campus-wide management & communication platform**.

---


---

# 📍 Smart Campus Navigation & Faculty Geotag Notification System

*(Features List – Excluding Role-Based Access)*

---

## 🔹 1. Campus Map Overlay

* AI-generated or fictional map used as the background.
* Buildings plotted as **nodes** with fixed `(x, y)` coordinates.
* Buildings displayed as circles/icons with labels.
* Interactive: tooltips or hover info show building details.

---

## 🔹 2. Shortest Path Finder

* Campus modeled as a **graph**:

  * Nodes = Buildings.
  * Edges = Paths with distances.
* Uses **Dijkstra’s Algorithm** (or A\*) to calculate shortest path.
* Path displayed as a **highlighted line** overlay on the map.
* Side panel shows distance and approximate walking time.

---

## 🔹 3. Animated Navigation

* A **moving dot** animates along the shortest path.
* Simulates real-world walking/navigation.
* Implemented using JavaFX `PathTransition`.
* Expansion: adjustable speed, pause/resume option.

---

## 🔹 4. Geotagging Locations (Event Creation)

* Any user (faculty, organizer, etc.) can **click on map** to place a marker.
* Input fields:

  * Event name/message.
  * Time (e.g., “3:00 PM”).
  * Type (Lecture, Lab, Club, Emergency).
* Marker stored in DB with `(x, y)` location and metadata.
* Marker appears on map with an icon (color/shape based on event type).

---

## 🔹 5. Multi-Event Markers

* Supports **multiple events** on the map at the same time.
* Each event marker has unique attributes (type, time, description).
* Students can **filter markers** (e.g., show only Labs or Emergencies).
* Clicking a marker → shortest path overlay + animated navigation.
* Old events automatically removed after expiry time.

---

## 🔹 6. Push Notifications

* When an event is geotagged, a **push notification** is generated.
* MVP: in-app popup alert (JavaFX `Alert` or `NotificationPane`).
* Expansion:

  * Mobile push via **Firebase Cloud Messaging (FCM)**.
  * Email or SMS notifications via third-party API.
* Notifications are interactive → click notification → open map → show event location + path.

---

## 🔹 7. Database Integration

* Local database (SQLite for MVP).
* Tables:

  * **Buildings** → `(id, name, x, y)`
  * **Paths** → `(id, fromBuilding, toBuilding, distance)`
  * **Events** → `(id, type, message, time, x, y)`
* Stores buildings, paths, and events persistently.
* Expansion: Migrate to MySQL/PostgreSQL for larger setups.

---

## 🔹 8. User Interface (MVP)

* **Main Map Panel**: Campus map with overlay nodes, paths, and event markers.
* **Side Panel**:

  * Dropdowns to select start & destination.
  * Distance/time output for navigation.
  * Event list (click → highlight on map).
* **Notification Pane**: Pops up with latest events.

---

# ✅ Summary

This project provides:

* A **digital campus navigation tool** (overlay + animated pathfinding).
* A **real-time geotag notification system** (multi-event markers + push alerts).
* A **simple but expandable design** (starts with in-app + SQLite, grows into full campus management).

---

