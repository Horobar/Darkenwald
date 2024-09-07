# Darkenwald Application Documentation

## 1. Introduction
- Darkenwald is a comprehensive tool designed for Dungeon Masters (DMs) to track and manage crucial game information in one centralized location.
- Purpose: To provide DMs with a structured, easily accessible system for organizing game elements and enhancing gameplay management.
- Goals:
    - Streamline information tracking for DMs
    - Facilitate efficient session preparation and real-time information retrieval during gameplay
    - Offer a flexible system for managing both planned and improvised game elements

## 2. System Architecture
- Backend: Quarkus with Kotlin
- Frontend: Server-side rendered using Qute, HTMX, and Tailwind
- Database: Hibernate with Panache, using PostgreSQL
- Deployment: K3s cluster on Raspberry Pi

## 3. Setup and Installation
[To be expanded based on development progress]

## 4. Core Features

### 4.1 Player Information Management
- Manually track player conditions
- Manually monitor player health
- Manage player affiliations (organizations)
- Record player contacts
- Notes system for each player:
    - Markdown content (stored as plain text, not rendered to HTML)
    - Tags (separated by semicolons)
    - Category field
- Activity logging for players:
    - Social interactions
    - Actions
    - Location changes
    - Story relevant findings
- Avatar system:
    - Visual representation of player
    - Indicators for conditions and effects

### 4.2 Setting Information
- Organization management
    - Associate NPCs with organizations
    - Associate locations with organizations
- Location tracking (cities, houses, rivers, etc.)
    - Track NPCs present at each location
- NPC database
    - Track current location of each NPC
    - Manage organizational affiliations for each NPC
    - Activity logging for NPCs:
        - Location changes
        - Relevant actions
        - Information received from players

### 4.3 Story Structure
- Scene-based story organization
- Network structure allowing non-linear progression
- For each scene, track:
    - Location
    - NPCs present
    - Events (player-triggered or time-based)
    - Story elements
    - Clues
    - Loot
    - Additional custom elements as needed
- Ability to change scenes based on:
    - Story progression
    - Location change
- Simple dropdown for scene navigation (first iteration)

### 4.4 Session Management
- Time tracking system based on interactions and activities

### 4.5 Information Tracking
- Activity log system
    - Log activities for both players and NPCs
    - Timestamp each activity
    - Describe the activity
    - Associate activity with specific player or NPC
    - Categorize activities (social interaction, action, location change, etc.)
- Searchable activity log viewer
    - Overlay window design
    - Table format for easy scanning
    - Search functionality

### 4.6 Visualization
- Future improvement: Node-based graph for visualizing scene connections
- First iteration: Simple dropdown for scene selection
- Player avatars with visual indicators for conditions and effects

## 5. Database Schema
[To be expanded based on the component relationships]

## 6. API Documentation
[To be expanded as development progresses]

## 7. User Interface

Main Navigation: Side menu with three main screens

1. Index Screen
    - Placeholder for future development

2. Campaign Screen
    - Interface for creating and playing scenes
    - Scene dropdown for navigation
    - Display of current scene details
    - Player avatars with condition/effect indicators
    - Quick access to activity logs for each entity

3. Library Screen
    - Creation and management of NPCs, Locations, and other setting assets
    - Interface for associating assets with scenes
    - Datalist for easy asset retrieval and association

Key Components:
1. Player Information Panel
    - Display and edit player stats, conditions, affiliations
    - Notes section for each player with markdown support, tags, and categories
    - Button to open activity log overlay

2. Setting Information Manager
    - Interfaces for managing organizations, locations, and NPCs
    - Activity log access for NPCs

3. Scene Manager
    - Tools for creating and editing scenes
    - Dropdown for scene navigation (first iteration)

4. Asset Library
    - Repository of all created NPCs, locations, and other setting elements
    - Datalist interface for adding these assets to scenes

5. Activity Log Viewer
    - Overlay window design
    - Searchable table format
    - Filter by entity type (player or NPC)
    - Sort by timestamp

6. Time Tracking System
    - Interface for managing and displaying in-game time based on logged activities

## 8. Security Considerations
[To be expanded]

## 9. Performance Optimization
[To be expanded]

## 10. Testing Strategy
[To be expanded]

## 11. Deployment Process
[To be expanded]

## 12. Maintenance and Support
[To be expanded]

## 13. Future Enhancements
- Advanced visualization features for scene connections
- Integration with other tools or systems
- [To be expanded based on future requirements]